# java-ac4y-guid8humanid

GUID-to-HumanID mapping domain, persistence, and service layer (legacy ID-suffix version).

## Coordinates

- **GroupId**: `ac4y`
- **ArtifactId**: `ac4y-guid8humanid`
- **Version**: `1.0.0`

## Description

Legacy version of the G8H (GUID-to-HumanID) mapping system using `ID` suffix naming convention (e.g., `getHumanID()` instead of `getHumanId()`). Contains domain objects, JDBC persistence adapters, and a service layer with JNDI connection management.

### Key Classes

- `Ac4yG8H` - Domain object (GUID, HumanID, TemplateGUID mapping)
- `Ac4yG8HList` / `Ac4yGUIDList` - Collection wrappers
- `Ac4yG8HDBAdapter` - JDBC persistence adapter
- `Ac4yG8HTemplateDBAdapter` - Template persistence operations
- `Ac4yIdentificationDBAdapter` - Identification view adapter
- `Ac4yGUID8HumanIDService` - Service layer with JNDI connection management

## Dependencies

- `ac4y-base4jsonandxml` (JSON/XML serialization)
- `ac4y-database-basic` (Ac4yDBAdapter)
- `ac4y-connection-pool` (DBConnection)
- `ac4y-service-domain` (Ac4yServiceOnDB)

## Build

```bash
mvn clean package
```

## Origin

Extracted from `IJAc4yGUID8HumanID/Ac4yGUID8HumanID`.
