package ted.wguc195.daos;

import javafx.collections.ObservableList;
import ted.wguc195.models.Country;

import java.sql.SQLException;

public interface CountryDao {
    public ObservableList<Country> getAllCountries() throws SQLException;
    public Country getCountry(int countryID) throws SQLException;
    public void updateCountry(Country country) throws SQLException;
    public void deleteCountry(int countryID) throws SQLException;
    public void addCountry(Country country) throws SQLException;
}
