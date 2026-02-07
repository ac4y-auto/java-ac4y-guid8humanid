package ac4y.guid8humanid.domain.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import ac4y.base.Ac4yException;
import ac4y.guid8humanid.domain.object.Ac4yG8H;

public class Ac4yG8HTemplateDBAdapter {

	public Ac4yG8HTemplateDBAdapter(Connection aConnection){
		
		setConnection(aConnection); 
	}

	private static String TEMPLATE = "template";
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	private Connection connection;

	public String getGUID(String aHumanID) throws SQLException, Ac4yException{

		return new Ac4yG8HDBAdapter(getConnection()).getGUID(TEMPLATE, aHumanID);
				
	} // getGUID
	
	public Ac4yG8H setByHumanID(String aHumanID) throws SQLException, Ac4yException{

		return new Ac4yG8HDBAdapter(getConnection()).setByHumanID(TEMPLATE, aHumanID);
				
	} // setByHumanID

	public Ac4yG8H getByGUID(String aGUID) throws SQLException, Ac4yException{

		//return new Ac4yG8HDBAdapter(getConnection()).getByHumanID(TEMPLATE, aGUID);
		return new Ac4yG8HDBAdapter(getConnection()).getByGUID(aGUID);
				
	} // getByGUID

	
	public boolean existByHumanID(String aHumanID) throws SQLException, Ac4yException{

		return new Ac4yG8HDBAdapter(getConnection()).isExistByHumanID(TEMPLATE, aHumanID);
				
	} // existByHumanID
	
	public Ac4yG8H setByGUIDAndHumanID(String aGUID, String aHumanID) throws SQLException, Ac4yException{
		
		return new Ac4yG8HDBAdapter(getConnection()).setByGUIDAndHumanID(aGUID, TEMPLATE, aHumanID);
		
	}
	
} // Ac4yG8HTemplate