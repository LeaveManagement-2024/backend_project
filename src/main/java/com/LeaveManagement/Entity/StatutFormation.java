package com.LeaveManagement.Entity;

public enum StatutFormation {
    PLANIFIEE("Planifiée"),
    EN_COURS("En cours"),
    TERMINEE("Terminée"),
    ANNULEE("Annulée"),
    REPORTEE("Reportée");

    private String label;

    StatutFormation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}