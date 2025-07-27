package com.microservice.lab.spreadsheet.services;

import com.microservice.lab.spreadsheet.model.XLSXTableDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class XLSXService {

    public XLSXTableDTO parseExcelFile(MultipartFile file) {
        List<String> headers = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (!rowIterator.hasNext()) {
                return new XLSXTableDTO(headers, rows); // archivo vacío
            }

            // Leer cabeceras
            Row headerRow = rowIterator.next();
            int numCols = headerRow.getLastCellNum();
            for (int i = 0; i < numCols; i++) {
                headers.add(getCellValueAsString(headerRow.getCell(i)));
            }

            // Leer filas del cuerpo
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                List<String> rowData = new ArrayList<>();
                for (int i = 0; i < numCols; i++) {
                    rowData.add(getCellValueAsString(row.getCell(i)));
                }
                rows.add(rowData);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al leer el archivo Excel: " + e.getMessage(), e);
        }

        return new XLSXTableDTO(headers, rows);
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return DateUtil.isCellDateFormatted(cell)
                    ? cell.getDateCellValue().toString()
                    : Double.toString(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return Boolean.toString(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.FORMULA) {
            return cell.getCellFormula();
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        }
        return "";
    }

}
