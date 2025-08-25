package com.spring.uploader_service.ExceptionHandler;

import com.spring.uploader_service.Dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;
import java.util.Date;

@ControllerAdvice
public class UploaderControllerAdvice {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponseDto> handleIoException(IOException exception) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder().timeStamp(new Date()).error("Bad Request").status(HttpStatus.BAD_REQUEST.value()).message(exception.getMessage()).build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponseDto> handleMaxSize(MaxUploadSizeExceededException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .timeStamp(new Date())
                .error("File too large")
                .status(HttpStatus.PAYLOAD_TOO_LARGE.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleDefaultException(Exception ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .timeStamp(new Date())
                .error("A generic error occurred")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleFileNotFoundException(RuntimeException ex) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .timeStamp(new Date())
                .error("File not found")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
