package com.project.config;

import com.project.util.Receipt;

import java.util.ArrayList;
import java.util.Map;

public class Order {

    private Map<Integer, Receipt> receipts;
    private double allowanceRate;
    private double mileageRate;
    private double allowance;
    private double mileage;
    private double sumOfCosts;

    public Map<Integer, Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(Map<Integer, Receipt> receipts) {
        this.receipts = receipts;
    }

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

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getSumOfCosts() {
        return sumOfCosts;
    }

    public void setSumOfCosts(double sumOfCosts) {
        this.sumOfCosts = sumOfCosts;
    }
}
