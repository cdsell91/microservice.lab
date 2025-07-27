package com.microservice.lab.spreadsheet.controllers;

import com.microservice.lab.spreadsheet.model.XLSXTableDTO;
import com.microservice.lab.spreadsheet.services.XLSXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/xlsx")
public class XLSXController {

    @Autowired
    private XLSXService XLSXService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public XLSXTableDTO uploadExcel(@RequestParam("file") MultipartFile file) {
        return XLSXService.parseExcelFile(file);
    }

}
