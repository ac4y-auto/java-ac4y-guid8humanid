package ac4y.guid8humanid.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import ac4y.base.Ac4yContext;
import ac4y.base.Ac4yException;
import ac4y.base.Ac4yProcess;
import ac4y.base.database.DBConnection;
import ac4y.base.domain.Ac4yIdentification;
import ac4y.guid8humanid.domain.object.Ac4yG8H;
import ac4y.guid8humanid.domain.object.Ac4yG8HList;
import ac4y.guid8humanid.domain.object.Ac4yGUIDList;
import ac4y.guid8humanid.domain.persistence.Ac4yG8HDBAdapter;
import ac4y.guid8humanid.domain.persistence.Ac4yG8HTemplateDBAdapter;
import ac4y.guid8humanid.domain.persistence.Ac4yIdentificationDBAdapter;
import ac4y.service.domain.Ac4yServiceOnDB;

public class Ac4yGUID8HumanIDService extends Ac4yServiceOnDB  {

	public String getPublicHumanID(String aHumanID) {
		return new Ac4yG8HDBAdapter().getPublicHumanID(aHumanID); 
	}
	
	public Ac4yG8H set(Ac4yIdentification aAc4yIdentification) throws ClassNotFoundException, SQLException, IOException, Ac4yException {

		if (aAc4yIdentification == null)
			throw new Ac4yException("identifcation is missing!");
		
		if (aAc4yIdentification.getHumanId() == null || aAc4yIdentification.getHumanId()=="")
			throw new Ac4yException("humanID is empty!");

		if (aAc4yIdentification.getTemplate().getHumanId() == null || aAc4yIdentification.getTemplate().getHumanId()=="")
			throw new Ac4yException("templateHumanID is empty!");

		if (!new Ac4yGUID8HumanIDService().existTemplateByHumanID(aAc4yIdentification.getTemplate().getHumanId())) {

			if (!(aAc4yIdentification.getTemplate().getGUID() == null) && !(aAc4yIdentification.getTemplate().getGUID()=="")) {

				new Ac4yGUID8HumanIDService().setTemplateByGUIDAndHumanID(
					aAc4yIdentification.getTemplate().getGUID()
					,aAc4yIdentification.getTemplate().getHumanId()
				);
				
			}
			
		}
		
		if (!(aAc4yIdentification.getGUID() == null) && !(aAc4yIdentification.getGUID()==""))
			return 
				new Ac4yGUID8HumanIDService().setByGUIDAndHumanIDs(
						aAc4yIdentification.getGUID()
						,aAc4yIdentification.getTemplate().getHumanId()
						,aAc4yIdentification.getHumanId()
				);
		else
			return 
				new Ac4yGUID8HumanIDService().setByHumanIDs(
						aAc4yIdentification.getTemplate().getHumanId()
						,aAc4yIdentification.getHumanId()
				);

	} // set
	
	public Ac4yG8H insert(String aGUID, String aTemplateGUID, String aHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
		
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).insert(aGUID, aTemplateGUID, aHumanID);
	
		dBConnection.getConnection().close();
		
		return result;
		
	} // insert

	public Ac4yG8H insert(String aTemplateGUID, String aHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
		
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).insert(aTemplateGUID, aHumanID);
	
		dBConnection.getConnection().close();
		
		return result;
		
	} // insert
	
	public Ac4yG8H insert(String aTemplateGUID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
		
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).insert(aTemplateGUID);
	
		dBConnection.getConnection().close();
		
		return result;
		
	} // insert
	
	
	public Ac4yG8H deleteByPersistentID(int aPersistentID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
		
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).deleteByPersistentID(aPersistentID);
	
		dBConnection.getConnection().close();
		
		return result;
		
	} // deleteByPersistentID
	
	public Ac4yG8H deleteByGUID(String aGUID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
		
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).deleteByGUID(aGUID);
	
		dBConnection.getConnection().close();
		
		return result;
		
	} // deleteByGUID
	
	
	public Ac4yG8H setByHumanIDs(String aTemplateHumanID, String aHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
		
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).setByHumanIDs(aTemplateHumanID, aHumanID);
	
		dBConnection.getConnection().close();
		
		return result;
		
	} // setByHumanIDs

	public Ac4yG8H setByHumanIDs(String aGUID, String aTemplateHumanID, String aHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
		
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).setByHumanIDs(aGUID, aTemplateHumanID, aHumanID);
	
		dBConnection.getConnection().close();
		
		return result;
		
	} // setByHumanIDs

	
	public String getGUIDByHumanIDs(String aTemplateHumanID, String aHumanID) throws ClassNotFoundException, SQLException, IOException, Ac4yException{
		  
		//System.out.println("service - getGUIDByHumanIDs");
		//System.out.println("templateHumanID:" + aTemplateHumanID);
		//System.out.println("humanID:" + aHumanID);
		
		if (aTemplateHumanID == null || aTemplateHumanID == "")
			throw new Ac4yException("templateHumanID is empty!");		
		
		String result = null;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).getGUIDByHumanIDs(aTemplateHumanID, aHumanID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // getGUIDByHumanIDs

	public String getGUIDByPersistentID(int aPersistentID) throws ClassNotFoundException, SQLException, IOException, Ac4yException{
		  
		//System.out.println("service - getGUIDByHumanIDs");
		
		String result = null;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).getGUIDByPersistentID(aPersistentID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // getGUIDByPersistentID

	
	
	public String getGUID(String aTemplateGUID, String aHumanID) throws ClassNotFoundException, SQLException, IOException, Ac4yException{
		  
		String result = null;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).getGUID(aTemplateGUID, aHumanID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // getGUID

	public String getTemplateGUID(String aTemplateHumanID) throws ClassNotFoundException, SQLException, IOException, Ac4yException{
		  
		String result = null;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HTemplateDBAdapter(dBConnection.getConnection()).getGUID(aTemplateHumanID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // getTemplateGUID

	
	public boolean isExistByPersistentID(int aPersistentID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		boolean result = false;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).isExistByPersistentID(aPersistentID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // isExistByGUID

	public boolean isExistByGUID(String aGUID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		boolean result = false;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).isExistByGUID(aGUID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // isExistByGUID

	public boolean isExistByHumanIDs(String aTemplateHumanID, String aHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		//System.out.println("service - isExistByHumanIDs - aTemplateHumanID:" + aTemplateHumanID + " aHumanID:" + aHumanID);
		
		boolean result = false;
		
		DBConnection dBConnection = getDBConnection();		
			
		//result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).isExistByGUID(getGUIDByHumanIDs(aTemplateHumanID, aHumanID) );
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).isExistByHumanIDs(aTemplateHumanID, aHumanID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // isExistByHumanIDs


	public boolean existByPersistentID(int aPersistentID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		boolean result = false;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).isExistByPersistentID(aPersistentID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // existByPersistentID

	public boolean existByGUID(String aGUID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		boolean result = false;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).isExistByGUID(aGUID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // existByGUID

	public boolean existByHumanIDs(String aTemplateHumanID, String aHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		//System.out.println("service - isExistByHumanIDs - aTemplateHumanID:" + aTemplateHumanID + " aHumanID:" + aHumanID);
		
		boolean result = false;
		
		DBConnection dBConnection = getDBConnection();		
			
		//result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).isExistByGUID(getGUIDByHumanIDs(aTemplateHumanID, aHumanID) );
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).isExistByHumanIDs(aTemplateHumanID, aHumanID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // existByHumanIDs

	
	public Ac4yG8H setByGUIDAndHumanIDs(String aGUID, String aTemplateHumanID, String aHumanID) throws ClassNotFoundException, SQLException, IOException, Ac4yException{

		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).setByGUIDAndHumanIDs(aGUID, aTemplateHumanID, aHumanID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // setByGUIDAndHumanIDs
	
	public Ac4yG8H setByGUIDAndHumanID(String aGUID, String aTemplateGUID, String aHumanID) throws ClassNotFoundException, SQLException, IOException, Ac4yException{

		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).setByGUIDAndHumanID(aGUID, aTemplateGUID, aHumanID);
		
		dBConnection.getConnection().close();
		
		return result;
	
	} // setByGUIDAndHumanID
	
	
	public Ac4yG8H setTemplateByHumanID(String aHumanID) throws ClassNotFoundException, SQLException, IOException, Ac4yException{

		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HTemplateDBAdapter(dBConnection.getConnection()).setByHumanID(aHumanID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // setTemplateByHumanID

	public Ac4yG8H setTemplateByGUIDAndHumanID(String aGUID, String aHumanID) throws ClassNotFoundException, SQLException, IOException, Ac4yException{

		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HTemplateDBAdapter(dBConnection.getConnection()).setByGUIDAndHumanID(aGUID, aHumanID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // setTemplateByGUIDAndHumanID

	public boolean existTemplateByHumanID(String aHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		  
		boolean result = false;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HTemplateDBAdapter(dBConnection.getConnection()).existByHumanID(aHumanID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // existTemplateByHumanID
	
	public Ac4yG8H getTemplateByHumanID(String aHumanID) throws ClassNotFoundException, SQLException, IOException, Ac4yException{

		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HTemplateDBAdapter(dBConnection.getConnection()).setByHumanID(aHumanID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // setTemplateByHumanID
	
	public Ac4yG8H getTemplateByGUID(String aGUID) throws ClassNotFoundException, SQLException, IOException, Ac4yException{

		Ac4yG8H result;
		
		DBConnection dBConnection = getDBConnection();		
			
		result = new Ac4yG8HTemplateDBAdapter(dBConnection.getConnection()).getByGUID(aGUID);
		
		dBConnection.getConnection().close();
		
		return result;
		
	} // getTemplateByGUID

	
	
	public int getPersistentIDByHumanIDs(String aTemplateHumanID, String aHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		int result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).getPersistentDByHumanIDs(aTemplateHumanID, aHumanID);
	
		dBConnection.getConnection().close();
		
		return result;

	} // getPersistentIDByHumanIDs
	
	public Ac4yG8H getByPersistentID(int aPersistentID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		Ac4yG8H ac4yG8H = new Ac4yG8HDBAdapter(dBConnection.getConnection()).getByPersistentID(aPersistentID);
	
		dBConnection.getConnection().close();
		
		return ac4yG8H;

	} // getByPersistentID
	
	public Ac4yG8H getByGUID(String aGUID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		Ac4yG8H ac4yG8H = new Ac4yG8HDBAdapter(dBConnection.getConnection()).getByGUID(aGUID);
	
		dBConnection.getConnection().close();
		
		return ac4yG8H;

	} // getByGUID
	
	public Ac4yIdentification getAc4yIdentificationByPersistentID(int aPersistentID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		Ac4yIdentification result = new Ac4yIdentificationDBAdapter(dBConnection.getConnection()).getByPersistentID(aPersistentID);
	
		dBConnection.getConnection().close();
		
		return result;

	} // getAc4yIdentificationByPersistentID
	
	public Ac4yIdentification getAc4yIdentificationByGUID(String aGUID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		Ac4yIdentification result = new Ac4yIdentificationDBAdapter(dBConnection.getConnection()).getByGUID(aGUID);
	
		dBConnection.getConnection().close();
		
		return result;

	} // getAc4yIdentificationByGUID
	
	public Ac4yIdentification getAc4yIdentificationByHumanIDs(String aTemplateHumanID, String aHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{
		
		return 	getAc4yIdentificationByPersistentID(
					getPersistentIDByHumanIDs(aTemplateHumanID, aHumanID)
				);
/*
		Ac4yG8H ac4yG8H = new Ac4yGUID8HumanIDService().getByHumanIDs(aTemplateHumanID, aHumanID);
		Ac4yIdentification ac4yIdentification = ac4yG8H.getAc4yIdentification();
		Ac4yG8H template=new Ac4yGUID8HumanIDService().getTemplateByGUID(ac4yG8H.getTemplateGUID());
		ac4yIdentification.setTemplate(template.getAc4yClass());
		
		return ac4yIdentification;
*/
	} // getAc4yIdentificationByHumanIDs
	
	public Ac4yG8H getByHumanID(String aTemplateGUID, String aHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		Ac4yG8H ac4yG8H = new Ac4yG8HDBAdapter(dBConnection.getConnection()).getByHumanID(aTemplateGUID, aHumanID);
	
		dBConnection.getConnection().close();
		
		return ac4yG8H;

	} // getByHumanID

	public Ac4yG8H getByHumanIDs(String aTemplateHumanID, String aHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		Ac4yG8H ac4yG8H = 
			new Ac4yG8HDBAdapter(dBConnection.getConnection()).getByHumanID(
				new Ac4yG8HTemplateDBAdapter(dBConnection.getConnection()).getGUID(aTemplateHumanID)			
				,aHumanID
			);
	
		dBConnection.getConnection().close();
		
		return ac4yG8H;

	} // getByHumanIDs
	
	public Ac4yG8HList getInstanceList(String aTemplateHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		Ac4yG8HList ac4yG8HList = 
				new Ac4yG8HDBAdapter(dBConnection.getConnection()).getList(
						"templateGUID = '"+getTemplateGUID(aTemplateHumanID)+"'"
				);
	
		dBConnection.getConnection().close();
		
		return ac4yG8HList;

	} // getInstanceList
	
	public int getInstanceListCount(String aTemplateHumanID) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		int result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).getInstanceListCount(aTemplateHumanID, "");
	
		dBConnection.getConnection().close();
		
		return result;

	} // getInstanceListCount
	
	public Object processOnInstanceList(String aTemplateGUID, String aWhere, Ac4yProcess aProcess, Object aProcessArgument) throws SQLException, Ac4yException, ClassNotFoundException, IOException, ParseException{

		DBConnection dBConnection = getDBConnection();		
		
		Object result = 
			new Ac4yG8HDBAdapter(dBConnection.getConnection()).processOnInstanceList(
				aTemplateGUID
				,aWhere
				,aProcess
				,aProcessArgument
			);
	
		dBConnection.getConnection().close();
		
		return result;
				
	} // processOnInstanceList
	
	
	public Ac4yG8HList getList(String aWhere) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		Ac4yG8HList ac4yG8HList = new Ac4yG8HDBAdapter(dBConnection.getConnection()).getList(aWhere);
		
		dBConnection.getConnection().close();
		
		return ac4yG8HList;

	} // getList

	public int getListCount(String aWhere) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		int result = new Ac4yG8HDBAdapter(dBConnection.getConnection()).getListCount(aWhere);
		
		dBConnection.getConnection().close();
		
		return result;

	} // getListCount
	
	public Ac4yGUIDList getGUIDList(String aWhere) throws SQLException, ClassNotFoundException, IOException, Ac4yException{

		DBConnection dBConnection = getDBConnection();		
		
		Ac4yGUIDList ac4yGUIDList = new Ac4yG8HDBAdapter(dBConnection.getConnection()).getGUIDList(aWhere);
	
		dBConnection.getConnection().close();
		
		return ac4yGUIDList;

	} // getGUIDList
	
} // Ac4yG8HService