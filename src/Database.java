import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private String db = "jdbc:sqlserver://localhost:1433;databaseName=Dev;trustServerCertificate=true;IntegratedSecurity=true;";
    private Connection c;
    private Statement selectStatement;
    // TODO: config the updateStament
    @SuppressWarnings("unused")
    private Statement updateStatemnet; 

    private ResultSet rs;
    

    public Database() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try {
            System.out.println("Connection to database...");
            c = DriverManager.getConnection(db);
            System.out.println("connected successfully!");
            selectStatement = c.createStatement();
            updateStatemnet = c.createStatement();
        } catch (Exception e) {
            System.out.println("database connection error: "+e.getMessage());
        }
    } 

    void close() throws SQLException{
        System.out.println("Closing connection to database...");
        c.close();
        System.out.println("connection closed successfully!");

    }

    void selectQuery(){
        try {
            rs = selectStatement.executeQuery("select * from Emp");
            while (rs.next()) {
                System.out.println("Query: "+rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("wrong Query entry"+e.getMessage());
        }
    }

    
}
