package com.app.mycoolapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel {
    public int status;
    public String message;
    public Object dataObject;
}
