import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DatabaseConnection {

	private String userName = "ae8081", password = "rotmos";
	private String answerSchema, bandNamn, scenNamn, bandfromDb = "", searchBandNamn = "";
	private String fromDb, scenfromDb, searchBandNamnDb = "", medlemmar = "", bandNamnMember= "";
	private String kp="", kpTop = "", kPerson ="", knamn= "";
	

	public void schema(String query) {
		fromDb = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://195.178.235.60:3306/ae8081",
												userName, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			answerSchema = "SpelSchema:\n\nTid:\t\t" + "Scen:\t\t" + "Bandnamn:\t" + "\n"; 
			while (rs.next()){
				System.out.println(bandfromDb);
				fromDb += rs.getString(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\n";
//				System.out.println("SpelSchema:\n\nTid:\t\t\t" + "Scen:\t\t" + "Bandnamn:\t" + "\n" 
//						+ rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
			}
				stmt.close();
		} catch (Exception e) {
			System.err.println(e);
		  }
	}
	
	public void band(String query){
		bandfromDb = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://195.178.235.60:3306/ae8081",
												userName, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			bandNamn = "BAND SOM SPELAR \n";
			while (rs.next()){
				bandfromDb += rs.getString(1) + "!\n";
			}
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
		  }
	}
	
	public void scen(String query){
		scenfromDb = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://195.178.235.60:3306/ae8081",
												userName, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			scenNamn = "Scen info... \n\n" + "Namn\t\t" + "Plats\t\t" + "Kapacitet\t" + "Scenansvarig\n" ;
			while (rs.next()){
				scenfromDb += rs.getString(1) + "\t\t" + rs.getString(2) + "\t" + rs.getString(3) +
						"\t" + rs.getString(4) + "\n";
			}
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
		  }
	}
	
	public void searchBand(String query){
		searchBandNamnDb = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://195.178.235.60:3306/ae8081",
												userName, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			searchBandNamn = "Info om bandet!\n\nUrsprung\tInfo\t\t\tGenre\t\tBandNamn\n";
			while (rs.next()){
				searchBandNamnDb += rs.getString(1) + "\t" + rs.getString(2) + "\t\t"  + rs.getString(3)
						+ "\t\t" + rs.getString(4)  + "\n";
			}
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
		  }
	}
	
	public void medlemmar (String query){
		medlemmar = "";
		String quest = "select Namn from Medlemmar where Bandnamn =" + "'" + query + "'";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://195.178.235.60:3306/ae8081",
												userName, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(quest);
			bandNamnMember = "Medlemmar:\n";
			while (rs.next()){
				medlemmar += rs.getString(1) + "\t";
			}
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
		  }
	}
	
	public void kontaktPerson(String query){
		kp = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://195.178.235.60:3306/ae8081",
												userName, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()){
				kp += rs.getString(1) + "\n";
			}
			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
		  }
	}
	
	public void kontaktNamn (String query){
		knamn ="";
		kpTop ="";
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://195.178.235.60:3306/ae8081",
												userName, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
				knamn = rs.getString(1);
			kpTop = knamn + " KontaktPerson för:\n";

			stmt.close();
		} catch (Exception e) {
			System.err.println(e);
		  }
	}

	public String getSchema(){
		return answerSchema + "\n"+ fromDb.replace("null", "");
	}
	
	public String getBandNamn(){
		return bandNamn  + "\n" + bandfromDb.replace("null", "");
	}
	
	public String getBandArray(){
		return bandfromDb;
	}
	
	public String getSearchBandnamn(){
		return searchBandNamn + "\n" + searchBandNamnDb;
	}
	
	public String getMedlemmar(){
		return bandNamnMember +  medlemmar;
	}
	
	public String getKontaktPerson(){
		return kp;
	}
	public String getKnanmn(){
		return kpTop;
	}
	
	public String getScen(){
		return scenNamn + "\n" + scenfromDb.replace("null", "").replace("Dieseltältet", "Dieseltältet      ");
	}
}
