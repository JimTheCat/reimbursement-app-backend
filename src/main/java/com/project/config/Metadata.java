package com.project.config;

import java.util.Map;

public class Metadata {
    private double allowanceRate;
    private double mileageRate;
    private double maxReimbursement;
    private Map<String, Double> receipts;

    public double getAllowanceRate() {
        return allowanceRate;
    }

    public void setAllowanceRate(double allowanceRate) {
        this.allowanceRate = allowanceRate;
    }

    public double getMileageRate() {
        return mileageRate;
    }

    public void setMileageRate(double mileageRate) {
        this.mileageRate = mileageRate;
    }

    public double getMaxReimbursement() {
        return maxReimbursement;
    }

    public void setMaxReimbursement(double maxReimbursement) {
        this.maxReimbursement = maxReimbursement;
    }

    public Map<String, Double> getReceipts() {
        return receipts;
    }

    public void setReceipts(Map<String, Double> receipts) {
        this.receipts = receipts;
    }
}
