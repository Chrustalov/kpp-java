package shop.Models;

import java.io.Serializable;
import java.time.LocalDate;
import com.google.gson.annotations.Expose;

public class Supplier implements Serializable {
    @Expose
    private String companyName;
    @Expose
    private LocalDate contractStartDate;

    public Supplier() {}

    public Supplier(String companyName, LocalDate contractStartDate) {
        this.companyName = companyName;
        this.contractStartDate = contractStartDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(LocalDate contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "companyName='" + companyName + '\'' +
                ", contractStartDate=" + contractStartDate +
                '}';
    }
}
