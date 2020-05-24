package com.aloneness.itoken.common.dto;

import java.io.Serializable;
import java.util.List;

public class BaseResult implements Serializable {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;
    private static final long serialVersionUID = 8561503472999877710L;

    private int status;
    private String message;
    private Object data;
    private Cursor cursor;
    private List<Error> errors;

    public static BaseResult success(Object data, Cursor cursor){
        return createResult(200, "success", data, cursor);
    }

    public static BaseResult success() {
        return BaseResult.createResult(STATUS_SUCCESS, "成功", null);
    }

    public static BaseResult success(String message) {
        return BaseResult.createResult(STATUS_SUCCESS, message, null);
    }

    public static BaseResult success(String message, Object data) {
        return BaseResult.createResult(STATUS_SUCCESS, message, data);
    }

    public static BaseResult fail() {
        return BaseResult.createResult(STATUS_FAIL, "失败", null);
    }

    public static BaseResult fail(String message) {
        return BaseResult.createResult(STATUS_FAIL, message, null);
    }

    public static BaseResult fail(int status, String message) {
        return BaseResult.createResult(status, message, null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public Cursor getCursor() {
        return cursor;
    }

    private static BaseResult createResult(int status, String message, Object data) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }

    private static BaseResult createResult(int status, String message, Object data, Cursor cursor) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        baseResult.setCursor(cursor);
        return baseResult;
    }

    public static class Cursor {
        private int total;
        private int offset;
        private int limit;

        public Cursor(){}

        public Cursor(int total, int offset, int limit) {
            this.total = total;
            this.offset = offset;
            this.limit = limit;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        @Override
        public String toString() {
            return "Cursor{" +
                    "total=" + total +
                    ", offset=" + offset +
                    ", limit=" + limit +
                    '}';
        }
    }
}
