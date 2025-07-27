package com.microservice.lab.spreadsheet.model;

import java.util.List;

public class XLSXTableDTO {

    private List<String> headers;
    private List<List<String>> rows;

    public XLSXTableDTO() {
    }

    public XLSXTableDTO(List<String> headers, List<List<String>> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public void setRows(List<List<String>> rows) {
        this.rows = rows;
    }
}
