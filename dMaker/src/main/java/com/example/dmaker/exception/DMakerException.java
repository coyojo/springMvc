package com.example.dmaker.exception;

import lombok.Getter;

// custom Exception 만들기
@Getter
public class DMakerException extends RuntimeException {
    private DMakerErrorCode dMakerErrorCode;
    private String detailMessage;

    public DMakerException(DMakerErrorCode errorCode){ // 에러 코드만 받아서 기본 에러메세지 띄어주는 생성자
        super(errorCode.getMessage());
        this.dMakerErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public DMakerException(DMakerErrorCode errorCode, String detailMessage){ // 원하는 메세지를 커스텀할 수 있게 만들어주는 메서드
        super(detailMessage);
        this.dMakerErrorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
