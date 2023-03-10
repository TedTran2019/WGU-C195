package ted.wguc195.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Division {
    private int divisionID;
    private String division;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    /**
     * Constructor for Division
     * @param divisionID
     * @param division
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param countryID
     */
    public Division(int divisionID, String division, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
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
     * @return the division
     * */
    public String getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     * */
    public void setDivision(String division) {
        this.division = division;
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
     * @return the countryID
     * */
    public int getCountryID() {
        return countryID;
    }

    /**
     * @param countryID the countryID to set
     * */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    @Override
    public String toString() {
        return division;
    }
}
