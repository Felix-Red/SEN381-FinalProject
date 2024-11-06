/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer;
import java.sql.Date;

/**
 *
 * @author user-pc
 */
public class Contract {
    private int contractId;
    private int clientId;
    private String contractTerms;
    private Date startDate;
    private Date endDate;
    private Date renewalDate;

    // Constructor
    public Contract(int contractId, int clientId, String contractTerms, Date startDate, Date endDate, Date renewalDate) {
        this.contractId = contractId;
        this.clientId = clientId;
        this.contractTerms = contractTerms;
        this.startDate = startDate;
        this.endDate = endDate;
        this.renewalDate = renewalDate;
    }

    public Contract() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters and Setters
    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getContractTerms() {
        return contractTerms;
    }

    public void setContractTerms(String contractTerms) {
        this.contractTerms = contractTerms;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }
}
