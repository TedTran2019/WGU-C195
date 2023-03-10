package ted.wguc195.daos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ted.wguc195.models.Division;
import ted.wguc195.utils.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Implementation of the DivisionDao interface.
 */
public class DivisionDaoImpl implements DivisionDao{
    /**
     * Gets all divisions from the database.
     * @return An ObservableList of all divisions.
     * @throws SQLException
     */
    @Override
    public ObservableList<Division> getAllDivisions() throws SQLException {
        ObservableList<Division> divisions = FXCollections.observableArrayList();

        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");

            divisions.add(new Division(divisionID, division, createDate.toLocalDateTime(), createdBy,
                    lastUpdate.toLocalDateTime(), lastUpdatedBy, countryID));
        }
        return divisions;
    }

    /**
     * Gets a division from the database.
     * @param divisionID The ID of the division to get.
     * @return The division with the given ID.
     * @throws SQLException
     */
    @Override
    public Division getDivision(int divisionID) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String division = rs.getString("Division");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");

            return new Division(divisionID, division, createDate.toLocalDateTime(), createdBy,
                    lastUpdate.toLocalDateTime(), lastUpdatedBy, countryID);
        }
        return null;
    }

    /**
     * Updates a division in the database.
     * @param division The division to update.
     * @throws SQLException
     */
    @Override
    public void updateDivision(Division division) throws SQLException {
        String sql = "UPDATE first_level_divisions SET Division = ?, Last_Update = ?, Last_Updated_By = ?, Country_ID = ?, WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, division.getDivision());
        ps.setTimestamp(2, Timestamp.valueOf(division.getLastUpdate()));
        ps.setString(3, division.getLastUpdatedBy());
        ps.setInt(4, division.getCountryID());
        ps.setInt(5, division.getDivisionID());
        ps.executeUpdate();
    }

    /**
     * Deletes a division from the database.
     * @param divisionID The ID of the division to delete.
     * @throws SQLException
     */
    @Override
    public void deleteDivision(int divisionID) throws SQLException {
        String sql = "DELETE FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, divisionID);
        ps.executeUpdate();
    }

    /**
     * Adds a division to the database.
     * @param division The division to add.
     * @throws SQLException
     */
    @Override
    public void addDivision(Division division) throws SQLException {
        String sql = "INSERT INTO first_level_divisions (Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, division.getDivision());
        ps.setTimestamp(2, Timestamp.valueOf(division.getCreateDate()));
        ps.setString(3, division.getCreatedBy());
        ps.setTimestamp(4, Timestamp.valueOf(division.getLastUpdate()));
        ps.setString(5, division.getLastUpdatedBy());
        ps.setInt(6, division.getCountryID());
        ps.executeUpdate();
    }

    /**
     * Gets all divisions from a given country.
     * @param countryID The ID of the country to get divisions from.
     * @return An ObservableList of all divisions from the given country.
     * @throws SQLException
     */
    @Override
    public ObservableList<Division> getDivisionsFromCountryID(int countryID) throws SQLException {
        ObservableList<Division> divisions = FXCollections.observableArrayList();

        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            divisions.add(new Division(divisionID, division, createDate.toLocalDateTime(), createdBy,
                    lastUpdate.toLocalDateTime(), lastUpdatedBy, countryID));
        }
        return divisions;
    }
}
