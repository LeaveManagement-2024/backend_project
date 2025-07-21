package com.LeaveManagement.Dto;

import java.util.ArrayList;
import java.util.List;

public class ImportResult {
    private int totalRecords;
    private int successCount;
    private int errorCount;
    private List<String> errors;

    public ImportResult() {
        this.errors = new ArrayList<>();
    }

    // Getters et Setters
    public int getTotalRecords() { return totalRecords; }
    public void setTotalRecords(int totalRecords) { this.totalRecords = totalRecords; }

    public int getSuccessCount() { return successCount; }
    public void setSuccessCount(int successCount) { this.successCount = successCount; }

    public int getErrorCount() { return errorCount; }
    public void setErrorCount(int errorCount) { this.errorCount = errorCount; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }

    public void addError(String error) { this.errors.add(error); }
}