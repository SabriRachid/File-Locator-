package jpa;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbDao
{
    InputStream inputStream = null;
    String selectTableSQL;

    public InputStream FindFile(String idDecompte)
    {
        try
        {
            System.out.println("connexion on");
            //chargement du driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.print("driver ok\n");
            String url = "jdbc:sqlserver://192.168.1.33:1433;databaseName=VdocDM";
            String user = "sa";
            String passwd = "P@$$w0rd";
            
            Connection con = DriverManager.getConnection(url, user, passwd);
            /*==============================Methode by omar============================================================
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("driver on");
            String connectionUrl = "jdbc:sqlserver://192.168.1.33:1433;databaseName=VdocDM;user=sa;password=Pa$$w0rd;";
            Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.1.33:1433;databaseName=VdocDM;user=sa;password=Pa$$w0rd;");
            ========================================================================================================= */
            System.out.println("url :"+  url);
            this.selectTableSQL = ("select FILENAME ,fileblob from THEFILES where FILENAME='" + idDecompte + "'");
            System.out.println("requette: " + this.selectTableSQL);
            Statement statement = con.createStatement();
            statement.setMaxRows(1);
            System.out.println("statement on");
            ResultSet rs = statement.executeQuery(this.selectTableSQL);
            System.out.println("statement executeQuery on");
            rs.next();

            String namefile = rs.getString(1);
            this.inputStream = rs.getBinaryStream(2);
            System.out.println("Traitement file: " + namefile);
            System.out.println("Taille file: " + this.inputStream.available());
        }
        catch (Exception e)
        {
            System.err.println("Sql" + e.getMessage());
            this.inputStream = null;
        }
        return this.inputStream;
    }
}
