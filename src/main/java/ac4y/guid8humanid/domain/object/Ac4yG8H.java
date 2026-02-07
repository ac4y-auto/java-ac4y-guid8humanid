package ac4y.guid8humanid.domain.object;

import javax.xml.bind.annotation.XmlRootElement;

import ac4y.base.Ac4yException;
import ac4y.base.domain.Ac4yClass;
import ac4y.base.domain.Ac4yIdentification;
import ac4y.base.domain.Ac4yNoId;

@XmlRootElement
public class Ac4yG8H extends Ac4yNoId {

	public Ac4yG8H(){}
	
	public Ac4yG8H(
			int aPersistentID
			,String aGUID
			,String aHumanID
			,String aPublicHumanID
			,String aSimpledHumanID
			,String aTemplateGUID
			,int aTemplatePersistentID) {
		
		setPersistentID(aPersistentID);
		setGUID(aGUID);
		setHumanID(aHumanID);
		setPublicHumanID(aPublicHumanID);
		setSimpledHumanID(aSimpledHumanID);
		setTemplateGUID(aTemplateGUID);
		setTemplatePersistentID(aTemplatePersistentID);
		
	}
	
	public String getGUID() {
		return GUID;
	}
	public void setGUID(String gUID) {
		GUID = gUID;
	}
	public String getHumanID() {
		return humanID;
	}
	public void setHumanID(String humanID) {
		this.humanID = humanID;
	}
	public String getPublicHumanID() {
		return publicHumanID;
	}
	public void setPublicHumanID(String publicHumanID) {
		this.publicHumanID = publicHumanID;
	}
	public String getSimpledHumanID() {
		return simpledHumanID;
	}
	public void setSimpledHumanID(String simpledHumanID) {
		this.simpledHumanID = simpledHumanID;
	}
	public String getTemplateGUID() {
		return templateGUID;
	}
	public void setTemplateGUID(String templateGUID) {
		this.templateGUID = templateGUID;
	}
	
	public int getPersistentID() {
		return persistentID;
	}

	public void setPersistentID(int persistentID) {
		this.persistentID = persistentID;
	}

	public int getTemplatePersistentID() {
		return templatePersistentID;
	}

	public void setTemplatePersistentID(int templatePersistentID) {
		this.templatePersistentID = templatePersistentID;
	}
	
	protected	String	GUID;
	protected	String	humanID;
	protected	String	publicHumanID;
	protected	String	simpledHumanID;
	protected	String	templateGUID;
	protected	int		persistentID;
	protected	int		templatePersistentID;
	protected 	boolean	deleted;
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Ac4yIdentification getAc4yIdentificationFromAc4yG8H(Ac4yG8H aAc4yG8H) throws Ac4yException {
		
		if (aAc4yG8H == null)
			throw new Ac4yException("aAc4yG8H is null!");
		
		return 
			new Ac4yIdentification(
				aAc4yG8H.getPersistentID()
				,aAc4yG8H.getGUID()
				,aAc4yG8H.getHumanID()
				,aAc4yG8H.getPublicHumanID()
				,aAc4yG8H.getTemplateGUID()
				,null
				,aAc4yG8H.isDeleted()
			);
		
	} // getAc4yObjectFromAc4yG8H

	public Ac4yIdentification getAc4yIdentification() throws Ac4yException {
		
		return getAc4yIdentificationFromAc4yG8H(this);
		
	} // getAc4yIdentification
		
	
	public Ac4yClass getAc4yClassFromAc4yG8H(Ac4yG8H aAc4yG8H) throws Ac4yException {
		
		if (aAc4yG8H == null)
			throw new Ac4yException("aAc4yG8H is null!");
		
		return 
			new Ac4yClass(
				aAc4yG8H.getPersistentID()
				,aAc4yG8H.getGUID()
				,aAc4yG8H.getHumanID()
				,aAc4yG8H.getPublicHumanID()
				,aAc4yG8H.isDeleted()
			);
		
	} // getAc4yObjectFromAc4yG8H

	public Ac4yClass getAc4yClass() throws Ac4yException {
		return getAc4yClassFromAc4yG8H(this);
	}
		
	
}