package ted.wguc195.daos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ted.wguc195.models.Country;
import ted.wguc195.utils.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CountryDaoImpl implements CountryDao{
    @Override
    public ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> countries = FXCollections.observableArrayList();

        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            countries.add(new Country(countryID, country, createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy));
        }
        return countries;
    }

    @Override
    public Country getCountry(int countryID) throws SQLException {
        String sql = "SELECT * FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String country = rs.getString("Country");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            return new Country(countryID, country, createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy);
        }
        return null;
    }

    @Override
    public void updateCountry(Country country) throws SQLException {
        String sql = "UPDATE countries SET Country = ?, Last_Update = ?, Last_Updated_By = ? WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, country.getCountry());
        ps.setTimestamp(2, Timestamp.valueOf(country.getLastUpdate()));
        ps.setString(3, country.getLastUpdatedBy());
        ps.setInt(4, country.getCountryID());
        ps.executeUpdate();
    }

    @Override
    public void deleteCountry(int countryID) throws SQLException {
        String sql = "DELETE FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryID);
        ps.executeUpdate();
    }

    @Override
    public void addCountry(Country country) throws SQLException {
        String sql = "INSERT INTO countries (Country, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, country.getCountry());
        ps.setTimestamp(2, Timestamp.valueOf(country.getCreateDate()));
        ps.setString(3, country.getCreatedBy());
        ps.setTimestamp(4, Timestamp.valueOf(country.getLastUpdate()));
        ps.setString(5, country.getLastUpdatedBy());
        ps.executeUpdate();
    }
}
