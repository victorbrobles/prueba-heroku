package urjc.isi.dao.implementaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

import urjc.isi.dao.interfaces.PersonasDAO;
import urjc.isi.entidades.Personas;

public class DirectoresDAOImpl extends GenericDAOImpl<Personas> implements PersonasDAO {

	public Personas fromResultSet(ResultSet rs) throws  SQLException{
		Personas persona = new Personas();

		persona.setId(rs.getString("idpersona"));
		persona.setFullNombre(rs.getString("fullnombre"));
		persona.setNacimiento(rs.getString("fnacimiento"));
		persona.setMuerte(rs.getString("fmuerte"));
		return persona;
	}

	@Override
	public void createTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.executeUpdate("create table directores" + table);
		c.commit();
	}

	@Override
   public void dropTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.executeUpdate("drop table if exists directores");
		c.commit();
	}

	@Override
	public void insert(Personas entity) {
	 	String sql = "INSERT INTO directores(idpersona,fullnombre,fnacimiento,fmuerte) VALUES(?,?,?,?)";

	  	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
	  		pstmt.setString(1, entity.getId());
	  		pstmt.setString(2, entity.getFullNombre());
	  		pstmt.setString(3, entity.getNacimiento());
	      	pstmt.setString(4, entity.getMuerte());
	  		pstmt.executeUpdate();
	    } catch (SQLException e) {
	  	    System.out.println(e.getMessage());
	  	}

	}

	@Override
	public void uploadTable(BufferedReader br) throws IOException, SQLException {
	    String s;
	    while ((s = br.readLine()) != null) {
	    	if(s.length()>0) {
		      Personas persona = new Personas(s);
		      insert(persona);
		      c.commit();
	    	}
	    }
	}

	@Override
	public List<Personas> selectAll() {
		List<Personas> personaslist = new ArrayList<>();
		  String sql = "SELECT * from directores";
		  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			  ResultSet rs = pstmt.executeQuery();
			  c.commit();
			  while(rs.next()){
				  personaslist.add(fromResultSet(rs));
			  }
	    } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return personaslist;
	}
	
	public List<Personas> selectAll(Dictionary<String,String> conditions) {
		List<Personas> directoresList = new ArrayList<>();
		  String sql = "SELECT * from directores as d ";
		  String cond = "WHERE ";
		  for(Enumeration<String> k = conditions.keys(); k.hasMoreElements();) {
				switch(k.nextElement()) {
					case "id_dir":
						cond+= "d.idpersona = " + conditions.get("id_dir");
						break;
					case "name":
						cond+= "d.fullnombre = " + conditions.get("name");
						break;
					case "fecha_nac":
						cond+= "d.fnacimiento = " + "'" + conditions.get("fecha_nac") + "'";
						break;
					case "intervalo_fecha_nac":
						String[] intervalo = conditions.get("intervalo_fecha_nac").split("-");
						cond+= "d.fnacimiento >= " + "'" + intervalo[0] + "'" + " and " + "d.fnacimiento <= "+ "'"+ intervalo[1] + "'" ;
						break;
					case "fecha_muer":
						cond+= "d.fmuerte = " + "'" + conditions.get("fecha_muer") + "'";
						break;
					case "intervalo_fecha_muer":
						String[] intervalo2 = conditions.get("intervalo_fecha_muer").split("-");
						cond+= "d.fmuerte >= " + "'" + intervalo2[0] + "'" + " and " + "d.fmuerte <= "+ "'"+ intervalo2[1] + "'" ;
						break;
				}
				if(k.hasMoreElements()) {
					cond+=" AND ";
				}
		  }
		  
		  try (PreparedStatement pstmt = c.prepareStatement(sql+cond)) {
			  ResultSet rs = pstmt.executeQuery();
			  c.commit();
			  while(rs.next()){
				  directoresList.add(fromResultSet(rs));
			  }
	    } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return directoresList;
	}
	

	@Override
	public Personas selectByID(String idpersona) {
		  String sql = "SELECT * from directores WHERE idpersona=" + idpersona;
		  Personas persona = new Personas();
		  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			  ResultSet rs = pstmt.executeQuery();
			  c.commit();
			  persona = fromResultSet(rs);
	      } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return persona;
	}

	@Override
	public void deleteByID(String idpersona) {
		  String sql = "DELETE from directores WHERE idpersona=" + idpersona;
		  try (PreparedStatement pstmt = c.prepareStatement(sql)){
			  pstmt.executeUpdate();
			  c.commit();
		  } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
	}
}
