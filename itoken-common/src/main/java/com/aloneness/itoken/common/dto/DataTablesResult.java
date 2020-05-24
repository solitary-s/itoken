package com.aloneness.itoken.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataTablesResult extends BaseResult implements Serializable {
    private static final long serialVersionUID = -6690260376320621994L;

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private String error;
}
