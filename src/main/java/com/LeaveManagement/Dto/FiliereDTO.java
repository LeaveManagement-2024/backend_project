package com.LeaveManagement.Dto;

public class FiliereDTO {
    private String FiliereNameFr;
    private long idService;

    public FiliereDTO() {
    }

    public FiliereDTO(String filiereNameFr, String filiereNameAr, long idService) {
        FiliereNameFr = filiereNameFr;
        this.idService = idService;
    }

    public String getFiliereNameFr() {
        return FiliereNameFr;
    }

    public void setFiliereNameFr(String filiereNameFr) {
        FiliereNameFr = filiereNameFr;
    }





    public long getIdService() {
        return idService;
    }

    public void setIdService(long idService) {
        this.idService = idService;
    }
}
