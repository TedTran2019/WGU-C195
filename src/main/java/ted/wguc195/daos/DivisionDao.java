package ted.wguc195.daos;

import javafx.collections.ObservableList;
import ted.wguc195.models.Division;

import java.sql.SQLException;
public interface DivisionDao {
    public ObservableList<Division> getAllDivisions() throws SQLException;
    public Division getDivision(int divisionID) throws SQLException;
    public void updateDivision(Division division) throws SQLException;
    public void deleteDivision(int divisionID) throws SQLException;
    public void addDivision(Division division) throws SQLException;
}
