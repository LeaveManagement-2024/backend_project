package com.LeaveManagement.Entity;

public enum TypeFormation {
    CERTIFIEE("Certifiée"),
    CONTINUE("Continue"),
    INTERNE("Interne");

    private String label;

    TypeFormation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}