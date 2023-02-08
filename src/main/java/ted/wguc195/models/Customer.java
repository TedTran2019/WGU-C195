package ted.wguc195.models;

import ted.wguc195.daos.DivisionDaoImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Customer {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;
    private DivisionDaoImpl divisionDao = new DivisionDaoImpl();

    /**
     * Constructor for Customer
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionID
     */
    public Customer(int customerID, String customerName, String address, String postalCode, String phone, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    /**
     * @return the customerID
     * */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     * */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the customerName
     * */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     * */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the address
     * */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     * */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postalCode
     * */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     * */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the phone
     * */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     * */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the createDate
     * */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     * */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the createdBy
     * */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     * */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the lastUpdate
     * */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate the lastUpdate to set
     * */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the lastUpdatedBy
     * */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy the lastUpdatedBy to set
     * */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return the divisionID
     * */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * @param divisionID the divisionID to set
     * */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * @return the divisionName
     * */
    public String getDivisionName() throws SQLException {
        return divisionDao.getDivision(divisionID).getDivision();
    }

    public String toString() {
        return customerName + " | ID: " + customerID;
    }
}
