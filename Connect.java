import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;




public class Connect {
	
	Connection conn=null;

	public Connection connect()  
	    {
		 
		Connection c = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:database_path");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	      
	     return c; 
	}
	
	
	public void insert(String name,double temp,String type) {
		
        String sql = "INSERT INTO veri (City,Temperature,Type) " +
              "VALUES (?,?,?);";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, temp);
            pstmt.setString(3, type);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

	}
	
	
	
	
	public void Update(double temp,String type,String name) {
		 
		        String sql = "UPDATE veri SET Temperature = ? , Type = ? " + "WHERE City = ?";

		        try (Connection conn = this.connect();
		                PreparedStatement pstmt = conn.prepareStatement(sql)) {

		           
		           pstmt.setDouble(1, temp);
		           pstmt.setString(2, type);
		           pstmt.setString(3, name);
		           pstmt.executeUpdate();
		          
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
	}
	
	
}
	 

