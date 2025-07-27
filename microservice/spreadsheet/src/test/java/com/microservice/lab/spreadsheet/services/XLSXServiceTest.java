package com.microservice.lab.spreadsheet.services;

import com.microservice.lab.spreadsheet.model.XLSXTableDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class XLSXServiceTest {

    private final XLSXService service = new XLSXService();

    @Test
    @DisplayName("Basic case of reading an xlsx file")
    void name() throws Exception {
        try (InputStream is = getClass().getResourceAsStream("/basic-xlsx-test.xlsx")) {
            assertNotNull(is, "Could not load XLSX file from resources");

            MockMultipartFile multipartFile = new MockMultipartFile(
                    "file",
                    "basic-xlsx-test.xlsx",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                    is
            );

            XLSXTableDTO result = service.parseExcelFile(multipartFile);

            assertEquals(3, result.getHeaders().size());
            assertEquals("H1", result.getHeaders().get(0));
            assertEquals("H2", result.getHeaders().get(1));
            assertEquals("H3", result.getHeaders().get(2));

            assertEquals(2, result.getRows().size());
            assertEquals("value1", result.getRows().get(0).get(0));
            assertEquals("value2", result.getRows().get(0).get(1));
            assertEquals("value3", result.getRows().get(0).get(2));

            assertEquals("value4", result.getRows().get(1).get(0));
            assertEquals("value5", result.getRows().get(1).get(1));
            assertEquals("value6", result.getRows().get(1).get(2));
        }
    }
}
