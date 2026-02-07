# Architektura - java-ac4y-guid8humanid

## Attekintes

A GUID-HumanID lekepezesi rendszer legacy verzioja az `ID` utotaggal (pl. `getHumanID()` a `getHumanId()` helyett). Domain objektumokat, JDBC adaptereket es szolgaltatas reteget tartalmaz.

## Szerkezet

```
src/main/java/ac4y/guid8humanid/
  domain/object/
    Ac4yG8H.java                    - Fo domain objektum (ID utotagu verzio)
    Ac4yG8HList.java                - G8H lista wrapper
    Ac4yGUIDList.java               - GUID lista wrapper
  domain/persistence/
    Ac4yG8HDBAdapter.java           - JDBC adatbazis adapter
    Ac4yG8HTemplateDBAdapter.java   - Template DB muveletek
    Ac4yIdentificationDBAdapter.java - Identification nezet adapter
  service/
    Ac4yGUID8HumanIDService.java    - Szolgaltatas reteg JNDI kapcsolatkezessel
src/test/java/ac4y/guid8humanid/
  domain/object/
    Ac4yG8HTest.java               - Ac4yG8H egyseg tesztek
    Ac4yG8HListTest.java           - Lista tesztek
    Ac4yGUIDListTest.java          - GUID lista tesztek
```

## Kapcsolat az ac4y-g8h-basic-kel

Ez a projekt az `ac4y-g8h-basic` regebbi verzioja. Fo kulonbseg az elnevezesi konvencio:
- **guid8humanid**: `HumanID`, `PersistentID` (nagybetus ID)
- **g8h-basic**: `HumanId`, `PersistentId` (kisbetus Id)

## Fuggetlensegek

- ac4y-base4jsonandxml (Ac4yNoId, JSON/XML)
- ac4y-database-basic (Ac4yDBAdapter)
- ac4y-connection-pool (DBConnection)
- ac4y-service-domain (Ac4yServiceOnDB)
- Teszt: JUnit 4, Mockito, H2

## Eredet

Az `IJAc4yGUID8HumanID/Ac4yGUID8HumanID` modulbol kinyerve.
