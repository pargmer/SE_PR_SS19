package model.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

public class DBTest {
	
	@Before
    public void setUp() throws SQLException, DatabaseUnitException, FileNotFoundException {
        // Dataset initialisieren
        IDataSet dataSet =
                new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml"));

        // Clean Insert = alles Loeschen und neu aufbauen
        try {
            DatabaseOperation.CLEAN_INSERT.execute(getConnection(), dataSet);
        } catch (DatabaseUnitException due) {
            throw due;
        } finally {
        }
    }

	 public IDatabaseConnection getConnection(){
	        return DBConnection.getConnection();
	     }

}
