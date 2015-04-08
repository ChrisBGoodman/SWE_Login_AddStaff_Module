package SWE_Login_AddStaff_Module;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class sqlTest {
        private String host = "localhost";
        private String database = "eattendance";
        private String user = "cgoodman";
        private String pass = "swe2015";

        public sqlTest () {
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://" + host
                                + "/" + database + "?"
                                + "user=" + user
                                + "&password=" + pass);
                    
                    DatabaseMetaData metadata = conn.getMetaData();

                    ResultSet resultSet = metadata.getTables(null, null, null, new String[]{"TABLE"});
                    
                    /*Statement st = conn.createStatement(
                            resultSet.TYPE_SCROLL_SENSITIVE,
                            resultSet.CONCUR_UPDATABLE);
                    */
                    
                   
                    /*while (resultSet.next()) {
                        System.out.println(resultSet.getString(3));
                    }*/
                    resultSet.close();

                    conn.close();
                } catch (SQLException ex) {
                    // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
        }

       /* public static void main(String[] args) {
                new sqlTest();
        }*/
        
        
        
        public void getStaff() throws SQLException
        {
            System.out.println("in sql");
            Connection conn = null;
            ResultSet rs = null;

            conn = DriverManager.getConnection("jdbc:mysql://" + host
                                + "/" + database + "?"
                                + "user=" + user
                                + "&password=" + pass);
            
            Statement st = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE,rs.CONCUR_UPDATABLE);
                    
            String query = "Select * from STAFF";
            String name, position, email;
                
            /*
            Use the statement to execute a query on the database and store the data in the result set. 
            Result set data must be navigated row by row with a cursor. The cursor begins in the first() row 
            and stores neccesary data before entering while loop. Loop will move cursor down row by row till
            reaching end of the table 
            */
            rs = st.executeQuery(query);                    
            rs.first();                                         
            name     = rs.getString("staff_name"); 
            position = rs.getString("position");
            email    = rs.getString("email");
                
            while(rs.next())
            {
                name     = rs.getString("staff_name");   
                position = rs.getString("position");
                email    = rs.getString("email");
            }

        } 
           
}



