import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import jpa.DbDao;
public class test {

    public static void main(String[] args) throws IOException {
        DbDao db = new DbDao();
        InputStream fileName = db.FindFile("AZ2C078A010_AZ2359065_CIN.PDF");
        System.out.println(fileName.available() + "");
    }

}
