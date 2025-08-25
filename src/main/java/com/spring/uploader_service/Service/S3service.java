package com.spring.uploader_service.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class S3service {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    // Upload a file to S3 and return the public URL
    public String upload(MultipartFile file) throws IOException {
        amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), new ObjectMetadata());
        return "File Uploaded " + file.getOriginalFilename();
    }

    public List<String> listFiles() {
        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();

        List<String> presignedUrls = new ArrayList<>();
        for (S3ObjectSummary os : objects) {
            // Set expiration for pre-signed URL (e.g., 1 hour)
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);

            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucketName, os.getKey())
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);

            URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
            presignedUrls.add(url.toString());
        }

        return presignedUrls;
    }

    public String deleteFile(String fileName) {
        if(!amazonS3.doesObjectExist(bucketName, fileName)) {
            throw new RuntimeException("File Not Found");
        }
        amazonS3.deleteObject(bucketName, fileName);
        return "File Deleted Successfully";
    }
}
