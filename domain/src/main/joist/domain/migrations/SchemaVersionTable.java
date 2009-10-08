package joist.domain.migrations;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import joist.jdbc.Jdbc;

/** A simple wrapper around the <code>schema_version</code> table for the {@link Migrater} class. */
public class SchemaVersionTable {

    private DataSource dataSource;

    public SchemaVersionTable(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean tryToLock() {
        // Assume locked if no table exists yet
        boolean notAround = Jdbc.queryForInt(this.dataSource, "select count(*) from information_schema.tables where table_name = 'schema_version'") == 0;
        if (notAround) {
            return true;
        }
        return Jdbc.update(this.dataSource, "UPDATE schema_version SET update_lock = 1 WHERE update_lock = 0") == 1;
    }

    public void unlock() {
        boolean around = Jdbc.queryForInt(this.dataSource, "select count(*) from information_schema.tables where table_name = 'schema_version'") == 1;
        if (around) {
            Jdbc.update(this.dataSource, "UPDATE schema_version SET update_lock = 0");
        }
    }

    public void vacuumIfAppropriate() {
        // Jdbc.update(this.dataSource, "vacuum analyze");
    }

    /** @param conn the auto-commit=false connection for the current update. */
    public int nextVersionNumber(Connection conn) throws SQLException {
        boolean notAround = Jdbc.queryForInt(conn, "select count(*) from information_schema.tables where table_name = 'schema_version'") == 0;
        if (notAround) {
            return 0;
        }
        return Jdbc.queryForInt(conn, "SELECT version FROM schema_version") + 1;
    }

    /** @param conn the auto-commit=false connection for the current update. */
    public void updateVersionNumber(Connection conn, int nextVersion) throws SQLException {
        Jdbc.update(conn, "UPDATE schema_version SET version = {}", nextVersion);
    }
}
