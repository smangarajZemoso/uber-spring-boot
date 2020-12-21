package com.app.mycoolapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTripModel {
    public long passengerId;
    public String startLocId;
    public String endLocId;
}
