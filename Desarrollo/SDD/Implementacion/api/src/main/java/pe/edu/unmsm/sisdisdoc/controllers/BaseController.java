package pe.edu.unmsm.sisdisdoc.controllers;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

public abstract class BaseController {
	
	protected static Connection cn = null;
    protected static ResultSet rs;
    protected static PreparedStatement ps;

}
