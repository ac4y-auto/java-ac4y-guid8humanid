package ac4y.guid8humanid.domain.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import ac4y.base.Ac4yException;
import ac4y.base.database.Ac4yDBAdapter;
import ac4y.base.domain.Ac4yClass;
import ac4y.base.domain.Ac4yIdentification;

public class Ac4yIdentificationDBAdapter extends Ac4yDBAdapter {

	public Ac4yIdentificationDBAdapter(Connection aConnection){
		super(aConnection); 
	}
	
	public Ac4yIdentification get(ResultSet aResultSet) throws SQLException {

		return 
			new Ac4yIdentification(
				aResultSet.getInt("persistentID")
				,aResultSet.getString("GUID")
				,aResultSet.getString("humanID")
				,aResultSet.getString("publicHumanID")
				,aResultSet.getInt("deleted")==1
				,new Ac4yClass (
						aResultSet.getInt("templatePersistentID")
						,aResultSet.getString("templateGUID")
						,aResultSet.getString("templateHumanID")
						,aResultSet.getString("templatePublicHumanID")
				)
			);
					
	} // get

	public Ac4yIdentification getByPersistentID(int aPersistentID) throws SQLException{
		
		Ac4yIdentification result = null;
		
		String 		sqlStatement 	= "SELECT * FROM vAc4yObject WHERE persistentID = " + aPersistentID;

		Statement 	statement 		= getConnection().createStatement();
		
		ResultSet resultSet = statement.executeQuery(sqlStatement);

		if (resultSet.next())
			result = get(resultSet);
		
		statement.close();

		return result;
				
	} // getByPersistentID
	
	public Ac4yIdentification getByGUID(String aGUID) throws SQLException, Ac4yException {

		if (aGUID == null || aGUID == "")
			throw new Ac4yException("GUID is empty!");
		
		Ac4yIdentification result = null;
		
		String sqlStatement = 
					"SELECT * FROM vAc4yObject WHERE"
					+ " GUID = '" + aGUID + "'"
					;		

		Statement 	statement 		= getConnection().createStatement();
		
		ResultSet resultSet = statement.executeQuery(sqlStatement);

		if (resultSet.next())
			result = get(resultSet);
		
		statement.close();

		return result;
				
	} // getByGUID
	
} // Ac4yIdentificationDBAdapter