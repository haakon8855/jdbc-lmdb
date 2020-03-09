package JDBC;


import java.sql.*;

public abstract class ActiveDomainObject {
    public abstract void initialize (Connection conn);
    public void refresh (Connection conn) {
    	this.initialize(conn);
    }
    public abstract void save (Connection conn);
}
