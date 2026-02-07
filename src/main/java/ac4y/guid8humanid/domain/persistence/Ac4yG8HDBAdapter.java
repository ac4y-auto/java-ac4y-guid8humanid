package ac4y.guid8humanid.domain.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ac4y.base.Ac4yException;
import ac4y.base.Ac4yProcess;
import ac4y.base.Ac4yProcessContext;
import ac4y.base.database.Ac4yDBAdapter;
import ac4y.guid8humanid.domain.object.Ac4yG8H;
import ac4y.guid8humanid.domain.object.Ac4yG8HList;
import ac4y.guid8humanid.domain.object.Ac4yGUIDList;
import ac4y.utility.GUIDHandler;
import ac4y.utility.StringHandler;

public class Ac4yG8HDBAdapter extends Ac4yDBAdapter {

	public Ac4yG8HDBAdapter() {
		
	}
	
	public Ac4yG8HDBAdapter(Connection aConnection){
		super(aConnection); 
	}

	public String getPublicHumanID(String aHumanID) {
		return new StringHandler().getLastPart(aHumanID, "\\.");
	}
	
	public Ac4yG8H insert(String aGUID, String aTemplateGUID, String aHumanID) throws SQLException, Ac4yException{

		String vGUID = aGUID;
		
		if 	(vGUID == null)
			vGUID = new GUIDHandler().getGUID();

		String vHumanID = aHumanID;

		if 	(vHumanID == null)
			vHumanID = vGUID;
		
		if (aTemplateGUID == null || aTemplateGUID == "")
			throw new Ac4yException("templateGUID is empty!");
		
		if (vHumanID == null || vHumanID == "")
			throw new Ac4yException("humanID is empty!");
		
		if (vGUID == null || vGUID == "")
			throw new Ac4yException("GUID is empty!");
		
		String vSQLStatement = "INSERT INTO Ac4yG8H"
				+ " (GUID, humanID, publicHumanID, simpledHumanID, templateGUID) VALUES"
				+ " (?,?,?,?,?)";
		
		String 	vSimpledHumanID = new StringHandler().getSimpled(vHumanID);
		String	vPublicHumanIDPart = new StringHandler().getLastPart(vHumanID, "\\.");
			
		PreparedStatement vPreparedStatement = 
			getConnection().prepareStatement(
				vSQLStatement
				,Statement.RETURN_GENERATED_KEYS
			);
		
		vPreparedStatement.setString(1, vGUID);
		vPreparedStatement.setString(2, vHumanID);
		vPreparedStatement.setString(3, vPublicHumanIDPart);
		vPreparedStatement.setString(4, vSimpledHumanID);
		vPreparedStatement.setString(5, aTemplateGUID);
		 
		if (vPreparedStatement.executeUpdate() == 0)
			throw new Ac4yException("insert failed!");

		ResultSet resultSet = vPreparedStatement.getGeneratedKeys();
		
		Ac4yG8H result = null;
			
		if (resultSet.next()) {
			
			result = 
				new Ac4yG8H(
					resultSet.getInt(1)
					,vGUID
					,vHumanID
					,vPublicHumanIDPart
					,vSimpledHumanID
					,aTemplateGUID
					,-2
				);
		}

		vPreparedStatement.close();
		
		return result;
		
	} // insert

	public Ac4yG8H insert(String aTemplateGUID, String aHumanID) throws SQLException, Ac4yException {

		return insert(null, aTemplateGUID, aHumanID);
		
	} // insert
	
	public Ac4yG8H insert(String aTemplateGUID) throws SQLException, Ac4yException{

		return insert(null, aTemplateGUID, null);
		
	} // insert

	public Ac4yG8H deleteByPersistentID(int aPersistentID) throws SQLException, Ac4yException{

		Ac4yG8H result = null;
/*		
		String sqlStatement = 
				"UPDATE Ac4yG8H "
				+ " SET deleted = 1"
				+ " WHERE "
				+ " persistentID = ? "
				;
*/
		String sqlStatement = 
				"DELETE FROM Ac4yG8H "
				+ " WHERE "
				+ " persistentID = " + aPersistentID
				;

		;

		//System.out.println("sqlStatement:"+sqlStatement);
/*		
		PreparedStatement preparedStatement = 
			getConnection().prepareStatement(
				sqlStatement
				,Statement.RETURN_GENERATED_KEYS
			);
		
		preparedStatement.setInt(1, aPersistentID);
		
		if (preparedStatement.executeUpdate() == 0) 
			throw new Ac4yException("logical delete failed!");
		
		else {
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			result = get(resultSet);
			
		}
		
		preparedStatement.close();
*/
		
		Statement statement = getConnection().createStatement();

		statement.executeUpdate(sqlStatement);		
		
		return result;
		
	} // deleteByPersistentID
	
	public Ac4yG8H deleteByGUID(String aGUID) throws SQLException, Ac4yException {

		Ac4yG8H result = null;
	/*	
		String sqlStatement = 
				"UPDATE Ac4yG8H "
				+ " SET deleted = 1"
				+ " WHERE "
				+ " GUID = ? "
				;
*/
		String sqlStatement = 
				"DELETE FROM Ac4yG8H "
				+ " WHERE "
				+ " GUID = ? "
				;
		
		
		PreparedStatement preparedStatement = getConnection().prepareStatement(sqlStatement);
		
		preparedStatement.setString(1, aGUID);
		
		if (preparedStatement.executeUpdate() == 0)
			throw new Ac4yException("logical delete failed!");
		else
			result = getByGUID(aGUID);
		
		preparedStatement.close();
		
		return result;
		
	} // deleteByGUID
	
	public Ac4yG8H setByGUIDAndHumanID(String aGUID, String aTemplateGUID, String aHumanID) throws SQLException, Ac4yException{

		if (!isExistByHumanID(aTemplateGUID, aHumanID))
			return insert(aGUID, aTemplateGUID, aHumanID);
		else {
			
			if (aGUID == null)
				return getByGUID(getGUID(aTemplateGUID, aHumanID)); 
			else
				return getByGUID(aGUID);
		}
				
	} // setByGUIDAndHumanID

	public Ac4yG8H setByHumanID(String aGUID, String aTemplateGUID, String aHumanID) throws SQLException, Ac4yException{

		if (!isExistByHumanID(aTemplateGUID, aHumanID))
			return insert(aGUID, aTemplateGUID, aHumanID);
		else {
			
			if (aGUID == null)
				return getByGUID(getGUID(aTemplateGUID, aHumanID)); 
			else
				return getByGUID(aGUID);
		}		
		
	} // setByHumanID
	
	public Ac4yG8H setByHumanID(String aTemplateGUID, String aHumanID) throws SQLException, Ac4yException{

		return setByGUIDAndHumanID(null, aTemplateGUID, aHumanID);
		
	} // setByHumanID
	
	public Ac4yG8H setByHumanIDs(String aTemplateHumanID, String aHumanID) throws SQLException, Ac4yException{

		new Ac4yG8HTemplateDBAdapter(getConnection()).setByHumanID(aTemplateHumanID);
		return setByHumanID(new Ac4yG8HTemplateDBAdapter(getConnection()).getGUID(aTemplateHumanID), aHumanID);
				
	} // setByHumanIDs
			
	public Ac4yG8H setByHumanIDs(String aGUID, String aTemplateHumanID, String aHumanID) throws SQLException, Ac4yException{

		new Ac4yG8HTemplateDBAdapter(getConnection()).setByHumanID(aTemplateHumanID);
		return setByHumanID(aGUID, new Ac4yG8HTemplateDBAdapter(getConnection()).getGUID(aTemplateHumanID), aHumanID);
		
	} // setByHumanIDs
			
	public Ac4yG8H setByGUIDAndHumanIDs(String aGUID, String aTemplateHumanID, String aHumanID) throws SQLException, Ac4yException{

		new Ac4yG8HTemplateDBAdapter(getConnection()).setByHumanID(aTemplateHumanID);
		return setByGUIDAndHumanID(aGUID, new Ac4yG8HTemplateDBAdapter(getConnection()).getGUID(aTemplateHumanID), aHumanID);
				
	} // setByGUIDAndHumanIDs

	
	public ResultSet getDoesExistByPersistentIDResultSet(Statement aStatement, int aPersistentID) throws SQLException, Ac4yException{
		
		if (aStatement == null)
			throw new Ac4yException("statement is null!");	

		String sqlStatement = "SELECT count(*) FROM Ac4yG8H WHERE persistentID = " + aPersistentID;
		
		return aStatement.executeQuery(sqlStatement);

	} // getDoesExistByPersistentIDResultSet
	
	public ResultSet getByPersistentIDResultSet(Statement aStatement, int aPersistentID) throws SQLException, Ac4yException{
		
		if (aStatement == null)
			throw new Ac4yException("statement is null!");	

		String sqlStatement = "SELECT * FROM Ac4yG8H WHERE persistentID = " + aPersistentID;
		
		return aStatement.executeQuery(sqlStatement);

	} // getByPersistentIDResultSet
	
	
	public boolean isExistByPersistentID(int aPersistentID) throws SQLException, Ac4yException{

		boolean	result = false;
			
		Statement statement = getConnection().createStatement();
		
		ResultSet resultSet = getDoesExistByPersistentIDResultSet(statement, aPersistentID);

		if (resultSet.next())
			result = resultSet.getInt(1)>0;

		statement.close();
		
		return result;
		
	} // isExistByPersistentID

	public boolean isExistByGUID(String aGUID) throws SQLException, Ac4yException{

		//System.out.println("adapter - isExistByGUID - GUID:" + aGUID );
		
		if (aGUID == null || aGUID == "")
			throw new Ac4yException("GUID is empty!");
		
		String 		vSQLStatement 	= "SELECT humanID FROM Ac4yG8H WHERE GUID = '" + aGUID + "'";
		Statement 	vStatement 		= null;
		boolean		vIsExists		= false;
			
		vStatement 		= getConnection().createStatement();
		
		ResultSet vResultSet = vStatement.executeQuery(vSQLStatement);

		vIsExists = vResultSet.isBeforeFirst();
		
		vStatement.close();
		
		return vIsExists;
		
	} // isExistByGUID

	
	public boolean isExistByHumanID(String aTemplateGUID, String aHumanID) throws SQLException, Ac4yException{

		if (aTemplateGUID == null || aTemplateGUID == "")
			throw new Ac4yException("templateGUID is empty!");		
		
		if (aHumanID == null || aHumanID == "")
			throw new Ac4yException("humanID is empty!");		
		
		String 		vSimpledHumanID = new StringHandler().getSimpled(aHumanID);
		String 		vSQLStatement 	= 
						"SELECT GUID FROM Ac4yG8H WHERE"
						+ " templateGUID = '" + aTemplateGUID + "'"
						+ " and simpledHumanID = '" + vSimpledHumanID + "'";		

		Statement 	vStatement 		= null;
		boolean		vIsExists		= false;
			
		vStatement 		= getConnection().createStatement();
		
		ResultSet vResultSet = vStatement.executeQuery(vSQLStatement);

		vIsExists = vResultSet.isBeforeFirst();
		
		vStatement.close();
		
		return vIsExists;
		
	} // isExistByHumanID
	
	public boolean isExistByHumanIDs(String aTemplateHumanID, String aHumanID) throws SQLException, Ac4yException{

		return (
				new Ac4yG8HTemplateDBAdapter(getConnection()).existByHumanID(aTemplateHumanID) &&
				isExistByHumanID(new Ac4yG8HTemplateDBAdapter(getConnection()).getGUID(aTemplateHumanID), aHumanID)
			);
		
	} // isExistsByHumanIDs
	
	
	public boolean doesExistByPersistentID(int aPersistentID) throws SQLException, Ac4yException{

		boolean	result = false;
			
		Statement statement = getConnection().createStatement();
		
		ResultSet resultSet = getDoesExistByPersistentIDResultSet(statement, aPersistentID);

		if (resultSet.next())
			result = resultSet.getInt(1)>0;

		statement.close();
		
		return result;
		
	} // doesExistByPersistentID

	public boolean doesExistByGUID(String aGUID) throws SQLException, Ac4yException{

		//System.out.println("adapter - isExistByGUID - GUID:" + aGUID );
		
		if (aGUID == null || aGUID == "")
			throw new Ac4yException("GUID is empty!");
		
		String 		vSQLStatement 	= "SELECT humanID FROM Ac4yG8H WHERE GUID = '" + aGUID + "'";
		Statement 	vStatement 		= null;
		boolean		vIsExists		= false;
			
		vStatement 		= getConnection().createStatement();
		
		ResultSet vResultSet = vStatement.executeQuery(vSQLStatement);

		vIsExists = vResultSet.isBeforeFirst();
		
		vStatement.close();
		
		return vIsExists;
		
	} // doesExistByGUID

	public boolean doesExistByHumanID(String aTemplateGUID, String aHumanID) throws SQLException, Ac4yException{

		if (aTemplateGUID == null || aTemplateGUID == "")
			throw new Ac4yException("templateGUID is empty!");		
		
		if (aHumanID == null || aHumanID == "")
			throw new Ac4yException("humanID is empty!");		
		
		String 		vSimpledHumanID = new StringHandler().getSimpled(aHumanID);
		String 		vSQLStatement 	= 
						"SELECT GUID FROM Ac4yG8H WHERE"
						+ " templateGUID = '" + aTemplateGUID + "'"
						+ " and simpledHumanID = '" + vSimpledHumanID + "'";		

		Statement 	vStatement 		= null;
		boolean		vIsExists		= false;
			
		vStatement 		= getConnection().createStatement();
		
		ResultSet vResultSet = vStatement.executeQuery(vSQLStatement);

		vIsExists = vResultSet.isBeforeFirst();
		
		vStatement.close();
		
		return vIsExists;
		
	} // doesExistByHumanID
	
	public boolean doesExistByHumanIDs(String aTemplateHumanID, String aHumanID) throws SQLException, Ac4yException{

		return (
				new Ac4yG8HTemplateDBAdapter(getConnection()).existByHumanID(aTemplateHumanID) &&
				isExistByHumanID(new Ac4yG8HTemplateDBAdapter(getConnection()).getGUID(aTemplateHumanID), aHumanID)
			);
		
	} // doesExistByHumanIDs
	
	public ResultSet getByHumanIDResultSet(Statement aStatement, String aTemplateGUID, String aHumanID) throws SQLException, Ac4yException{
		
		if (aTemplateGUID == null || aTemplateGUID == "")
			throw new Ac4yException("templateGUID is empty!");		
		
		if (aHumanID == null || aHumanID == "")
			throw new Ac4yException("humanID is empty!");		
		
		if (aStatement == null)
			throw new Ac4yException("statement is null!");	
		
		String 		simpledHumanID = new StringHandler().getSimpled(aHumanID);
		String 		sqlStatement 	= 
						"SELECT * FROM Ac4yG8H WHERE"
						+ " templateGUID = '" + aTemplateGUID + "'"
						+ " and simpledHumanID = '" + simpledHumanID + "'";		
		
		return aStatement.executeQuery(sqlStatement);

	} // getByHumanIDResultSet
	
	public String getGUIDByHumanIDs(String aTemplateHumanID, String aHumanID) throws SQLException, Ac4yException{

		//System.out.println("adapter - getGUIDByHumanIDs  - aTemplateHumanID:" + aTemplateHumanID + " aHumanID:" + aHumanID);
		
		return getGUID(new Ac4yG8HTemplateDBAdapter(getConnection()).getGUID(aTemplateHumanID), aHumanID);
		
	} // getGUIDByHumanIDs
	
	public String getGUIDByPersistentID(int aPersistentID) throws SQLException, Ac4yException{

		String result = null;
			
		Statement statement = getConnection().createStatement();
		
		ResultSet resultSet = getByPersistentIDResultSet(statement, aPersistentID);

		if (resultSet.next())
			result = resultSet.getString("GUID");

		statement.close();
		
		return result;
		
	} // getGUIDByPersistentID

	
	
	public int getPersistentID(String aTemplateGUID, String aHumanID) throws SQLException, Ac4yException{

		if (aTemplateGUID == null || aTemplateGUID == "")
			throw new Ac4yException("templateGUID is empty!");		
		
		if (aHumanID == null || aHumanID == "")
			throw new Ac4yException("humanID is empty!");		

		String simpledHumanID = new StringHandler().getSimpled(aHumanID);
		String sqlStatement 	= 
						"SELECT persistentID FROM Ac4yG8H WHERE"
						+ " templateGUID = '" + aTemplateGUID + "'"
						+ " and simpledHumanID = '" + simpledHumanID + "'";		

		Statement statement = null;
		int result = -1;
			
		statement 		= getConnection().createStatement();
		
		ResultSet resultSet = statement.executeQuery(sqlStatement);

		if (resultSet.next()){

			result = resultSet.getInt("persistentID");

		}
		
		statement.close();

		return result;
		
	} // getPersistentID
	
	public int getPersistentDByHumanIDs(String aTemplateHumanID, String aHumanID) throws SQLException, Ac4yException{

		return getPersistentID(new Ac4yG8HTemplateDBAdapter(getConnection()).getGUID(aTemplateHumanID), aHumanID);
		
	} // getPersistentDByHumanIDs

	
	public String getGUID(String aTemplateGUID, String aHumanID) throws SQLException, Ac4yException{

		//System.out.println("adapter - getGUID - aTemplateGUID:" + aTemplateGUID + " aHumanID:" + aHumanID + " getSimpledHumanID:" + new StringHandler().getSimpled(aHumanID));		
		
		if (aTemplateGUID == null || aTemplateGUID == "")
			throw new Ac4yException("templateGUID is empty!");		
		
		if (aHumanID == null || aHumanID == "")
			throw new Ac4yException("humanID is empty!");		
		
		String 		vSimpledHumanID = new StringHandler().getSimpled(aHumanID);
		String 		vSQLStatement 	= 
						"SELECT GUID FROM Ac4yG8H WHERE"
						+ " templateGUID = '" + aTemplateGUID + "'"
						+ " and simpledHumanID = '" + vSimpledHumanID + "'";		

		Statement 	vStatement 		= null;
		String		vGUID			= null;
			
		vStatement 		= getConnection().createStatement();
		
		vStatement.execute(vSQLStatement);
		
		ResultSet vResultSet = vStatement.executeQuery(vSQLStatement);

		if (vResultSet.next()){

			vGUID = vResultSet.getString("GUID");

		}
		
		vStatement.close();

		//System.out.println("adapter - getGUID - SQL statement: " + vSQLStatement + " result:" + vGUID);
		
		return vGUID;
		
	} // getGUID
	
	public Ac4yG8H get(ResultSet aResultSet) throws SQLException {

		return 
			new Ac4yG8H(
					aResultSet.getInt("persistentID")
					,aResultSet.getString("GUID")
					,aResultSet.getString("humanID")
					,aResultSet.getString("publicHumanID")
					,aResultSet.getString("simpledHumanID")
					,aResultSet.getString("templateGUID")
					,aResultSet.getInt("templatePersistentID")
			);

	} // get	

	public Ac4yG8H getByPersistentID(int aPersistentID) throws SQLException{
		
		Ac4yG8H result = null;
		
		String 		sqlStatement 	= "SELECT * FROM Ac4yG8H WHERE persistentID = " + aPersistentID;

		Statement 	statement 		= getConnection().createStatement();
		
		ResultSet resultSet = statement.executeQuery(sqlStatement);

		if (resultSet.next())
			result = get(resultSet);
		
		statement.close();

		return result;
				
	} // getByPersistentID
	
	public Ac4yG8H getByGUID(String aGUID) throws SQLException, Ac4yException {

		if (aGUID == null || aGUID == "")
			throw new Ac4yException("GUID is empty!");
		
		Ac4yG8H result = null;
		
		String sqlStatement = 
					"SELECT * FROM Ac4yG8H WHERE"
					+ " GUID = '" + aGUID + "'"
					;		

		Statement 	statement 		= getConnection().createStatement();
		
		ResultSet resultSet = statement.executeQuery(sqlStatement);

		if (resultSet.next())
			result = get(resultSet);
		
		statement.close();

		return result;
				
	} // getByGUID
	
	public Ac4yG8H getByHumanID(String aTemplateGUID, String aHumanID) throws SQLException, Ac4yException {

		//System.out.println("aTemplateGUID:" + aTemplateGUID);
		//System.out.println("aHumanID:" + aHumanID);
		
		if (aTemplateGUID == null || aTemplateGUID == "")
			throw new Ac4yException("templateGUID is empty!");		
		
		if (aHumanID == null || aHumanID == "")
			throw new Ac4yException("humanID is empty!");		
		
		Ac4yG8H result = null;
		
		Statement statement = getConnection().createStatement();
		
		ResultSet resultSet = getByHumanIDResultSet(statement, aTemplateGUID, aHumanID);

		if (resultSet.next())
			result = get(resultSet);
			
		statement.close();
		
		return result;
				
	} // getByHumanID

	public ResultSet getListResultSet(Statement aStatement, String aWhere) throws SQLException, Ac4yException{
		
		if (aStatement == null)
			throw new Ac4yException("statement is null!");	
		
		String where = "";
		
		if ((aWhere==null)||(aWhere==""))
			where = "1=1";
		else
			where = aWhere;
			
		String sqlStatement = "SELECT * FROM Ac4yG8H WHERE " + where;
		
		return aStatement.executeQuery(sqlStatement);

	} // getListResultSet
	
	public Ac4yG8HList getList(String aWhere) throws SQLException, Ac4yException{
		
		Ac4yG8HList result = new Ac4yG8HList();
			
		Statement statement = getConnection().createStatement();
		
		ResultSet resultSet = getListResultSet(statement, aWhere);

		while (resultSet.next())
			result.getAc4yG8H().add(get(resultSet));
		
		statement.close();

		return result;
				
	} // getList

	public int getListCount(String aWhere) throws SQLException, Ac4yException {
		
		int result = -2;
			
		Statement statement = getConnection().createStatement();
		
		ResultSet resultSet = getListResultSet(statement, aWhere);

		resultSet.next();
		
		result = resultSet.getRow();
		
		statement.close();

		return result;
				
	} // getListCount

	
	public Object processOnList(String aWhere, Ac4yProcess aProcess) throws SQLException, Ac4yException, ClassNotFoundException, IOException, ParseException{

		Object result = null;
		
		if (aProcess == null)
			throw new Ac4yException("illegal process!");	
		
		Statement statement = getConnection().createStatement();
		
		ResultSet resultSet = getListResultSet(statement, aWhere);

		while (resultSet.next())
			result = aProcess.process(resultSet);
		
		statement.close();

		return result;
				
	} // processOnList

	

	public ResultSet getInstanceListResultSet(Statement aStatement, String aTemplateGUID, String aWhere) throws SQLException, Ac4yException{
		
		if (aStatement == null)
			throw new Ac4yException("statement is null!");	
		
		String where = "";
		
		if ((aWhere==null)||(aWhere==""))
			where = " and 1=1";
		else
			where = aWhere;
			
		String sqlStatement = 
			"SELECT * "
			+ " FROM Ac4yG8H "
			+ " WHERE "
			+ " templateGUID = '" + aTemplateGUID + "'"
			+ where;
		
		return aStatement.executeQuery(sqlStatement);

	} // getInstanceListResultSet
	
	public Ac4yG8HList getInstanceList(String aTemplateGUID, String aWhere) throws SQLException, Ac4yException{
		
		Ac4yG8HList result = new Ac4yG8HList();
			
		Statement statement = getConnection().createStatement();
		
		ResultSet resultSet = getListResultSet(statement, aWhere);


		while (resultSet.next())
			result.getAc4yG8H().add(get(resultSet));
		
		statement.close();

		return result;
				
	} // getInstanceList

	public int getInstanceListCount(String aTemplateGUID, String aWhere) throws SQLException, Ac4yException {
		
		int result = -2;
			
		Statement statement = getConnection().createStatement();
		
		ResultSet resultSet = getInstanceListResultSet(statement, aTemplateGUID, aWhere);

		resultSet.next();
		
		result = resultSet.getRow();
		
		statement.close();

		return result;
				
	} // getInstanceListCount

	public Object processOnInstanceList(String aTemplateGUID, String aWhere, Ac4yProcess aProcess,Object aProcessArgument) throws SQLException, Ac4yException, ClassNotFoundException, IOException, ParseException {

		Object result = null;
		
		if (aProcess == null)
			throw new Ac4yException("illegal process!");	
		
		Statement statement = getConnection().createStatement();
		
		ResultSet resultSet = getInstanceListResultSet(statement, aTemplateGUID, aWhere);

		while (resultSet.next()) {

			//System.out.println("instance");
			
			result = //aProcess.process(resultSet);
					aProcess.process(
						new Ac4yProcessContext(
								aProcessArgument,
								resultSet
						)
					);

		}
		
		
		statement.close();

		return result;
				
	} // processOnInstanceList
	
	
	public Ac4yGUIDList getGUIDList(String aWhere) throws SQLException{
		
		Ac4yGUIDList ac4yGUIDList = new Ac4yGUIDList();

		String where = "";
		
		if ((aWhere==null)||(aWhere==""))
			where = "1=1";
		else
			where = aWhere;
			
		String sqlStatement = "SELECT GUID FROM Ac4yG8H WHERE " + where;

		Statement statement = getConnection().createStatement();
		
		statement.execute(sqlStatement);
		
		ResultSet resultSet = statement.executeQuery(sqlStatement);

		while (resultSet.next()){

			ac4yGUIDList.getGUID().add(resultSet.getString("GUID"));

		}
		
		statement.close();

		return ac4yGUIDList;
				
	} // getGUIDList
	
} // Ac4yG8H