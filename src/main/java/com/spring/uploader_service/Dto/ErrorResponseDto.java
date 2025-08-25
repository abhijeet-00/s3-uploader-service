package com.spring.uploader_service.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ErrorResponseDto {

    private int status;

    private String error;

    private Date timeStamp;

    private String message;
}
