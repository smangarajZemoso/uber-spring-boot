package com.app.mycoolapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupModel {
    public String type;
    public String name;
    public String phoneNo;
    public String gender;
    public String cabId;

    public SignupModel(String type, String name, String phoneNo, String gender, String cabId) {
        this.type = type;
        this.name = name;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.cabId = cabId;
    }
}
