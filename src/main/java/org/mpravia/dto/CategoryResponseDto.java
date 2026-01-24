package org.mpravia.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CategoryResponseDto {

    private long id;
    private String name;
    private String description;
    private OffsetDateTime createDate;
    private OffsetDateTime changeDate;

}
