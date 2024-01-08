import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;
import java.util.Stack;

/* ++++++++++++++++++++++++++++++++++++++++++++++
Make sure you did the following before execution
1) Connect to WPI's wifi or vpn
2) Create an Oracle data source and successfully create a connection
3) Write your java code (say file name is OracleTest.java) and then compile it
using the following command
> javac OracleTest.java
4) Run it
> java OracleTest
++++++++++++++++++++++++++++++++++++++++++++++ */

// Team: Preet Patel, Ronit Kapoor

//INFO IF NEEDED BY GRADER:
// Username is: postgres
//Password is: Post@724

public class p3 {
    public static void main(String[] args) throws SQLException {

        String paramMsg;
        paramMsg = "Syntax:  java p3 <username> <password> <connection string> <menu item>\n\n"
                + "Include the following number of the menu item as your fourth parameter:\n"
                + "1 â€“ Report Location Information\n"
                + "2 â€“ Report Edge Information\n"
                + "3 â€“ Report CS Staff Information\n"
                + "4 â€“ Insert New Phone Extension\n";
       if(args.length != 4){
           System.out.println(paramMsg);
           System.exit(0);
       }
        String USERID = args[0];
        String PASSWORD = args[1];
        String CONNECTIONSTRING = args[2];
        int MENUITEM = Integer.parseInt(args[3]);


        Connection connection = null;



        // String blocks are available in Java 15 and newer
        /* paramMsg = """
                            Syntax:  java p3 <username> <password> <connection string> <menu item>

                            Include the following number of the menu item as your fourth parameter:
                            1 â€“ Report Location Information
                            2 â€“ Report Edge Information
                            3 â€“ Report CS Staff Information
                            4 â€“ Insert New Phone Extension
                """;
         */

        if (args.length != 4) {
            System.out.println("Syntax: java p3 <username> <password> <connection string> <menu item>\n");
            System.out.println(paramMsg);
        }

        // System.out.println("-------Oracle JDBC Connection Testing ---------");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Post JDBC Driver?");
            // e.printStackTrace();
        }

        System.out.println("Oracle JDBC Driver Registered!");

        try {
            connection = DriverManager.getConnection(CONNECTIONSTRING, USERID, PASSWORD);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        switch (MENUITEM) {
            case (1): locations(connection); break;
            case (2): edges(connection); break;
            case (3): csstaff(connection); break;
            case (4): insertPhone(connection); break;
            default:
                System.out.println("Invalid menu item choice. Choose a value from 1 to 4");
                break;
        }
    }

    public static void locations (Connection connection) throws SQLException {
        try {
            PreparedStatement pstmt = connection.prepareStatement("Select * from Locations where locationID = ?");
            Scanner obj = new Scanner(System.in);
            System.out.println("Enter Location ID: ");
            String locationID = obj.nextLine();
            pstmt.setString(1, locationID);
            ResultSet table = pstmt.executeQuery();
            table.next();
            System.out.println("Location Information");
            System.out.println("Location ID: " + table.getString("locationID") + "\nLocation Name: " + table.getString("locationName") + "\nLocation Type: " + table.getString("locationType") + "\nX-Coordinate: " + table.getInt("xcoord") + "\nY-Coordinate: " + table.getInt("ycoord") + "\nFloor: " + table.getString("mapFloor"));
        }
        catch(SQLException e) {
                System.out.println("SQLException has risen.");
        }


    }

    public static void edges (Connection connection) throws SQLException {
        try {
            PreparedStatement pstmt = connection.prepareStatement("Select * from Edges where edgeID = ?");
            Scanner obj = new Scanner(System.in);
            System.out.println("Enter Edge ID: ");
            String edgeID = obj.nextLine();
            pstmt.setString(1, edgeID);
            ResultSet edgeTable = pstmt.executeQuery();
            edgeTable.next();
            PreparedStatement startloc = connection.prepareStatement("Select * from Locations where locationID = ?");
            startloc.setString(1, edgeTable.getString("startingLocationID"));
            ResultSet start = startloc.executeQuery();
            start.next();
            PreparedStatement endloc = connection.prepareStatement("Select * from Locations where locationID = ?");
            endloc.setString(1, edgeTable.getString("endingLocationID"));
            ResultSet end = endloc.executeQuery();
            end.next();
            System.out.println("Edges Information");
            System.out.println("EdgeID: " + edgeID + "\nStarting Location Name: " + start.getString("locationName") + "\nStarting Location Floor: " + start.getString("mapFloor"));
            System.out.println("Ending Location Name: " + end.getString("locationName") + "\nEnding Location Floor: " + end.getString("mapFloor"));
        }
        catch(SQLException e){
            System.out.println("SQLException has risen.");
        }
    }
    public static void csstaff (Connection connection) throws SQLException{
        try {
            PreparedStatement pstmt = connection.prepareStatement("Select * from CSStaff where accountName = ?");
            Scanner obj = new Scanner(System.in);
            System.out.println("Enter CS Staff Account Name: ");
            String accountName = obj.nextLine();
            pstmt.setString(1, accountName);
            ResultSet staffinfo = pstmt.executeQuery();
            staffinfo.next();
            System.out.println("CS Staff Information");
            System.out.println("Account Name:" + accountName + "\nFirst Name: " + staffinfo.getString("firstName") + "\nLast Name: " + staffinfo.getString("lastName") + "\nOffice ID: " + staffinfo.getString("officeID"));


            PreparedStatement gettitles = connection.prepareStatement("Select * from CSStaffTitles where accountName = ?");
            gettitles.setString(1, accountName);
            ResultSet cstitles = gettitles.executeQuery();
            System.out.print("Title: ");
            while (cstitles.next()) {
                PreparedStatement getfulltitle = connection.prepareStatement("Select titleName from Titles where acronym = ?");
                getfulltitle.setString(1, cstitles.getString("acronym"));
                ResultSet title = getfulltitle.executeQuery();
                title.next();
                System.out.print(title.getString("titleName") + ", ");


            }


            PreparedStatement getallphone = connection.prepareStatement("Select phoneExt from PhoneExtensions where accountName = ?");
            getallphone.setString(1, accountName);
            ResultSet phones = getallphone.executeQuery();
            System.out.print("\nPhone Ext: ");
            while (phones.next()) {

                System.out.print(phones.getString("phoneExt") + ", ");

            }
        }
        catch (SQLException e){
            System.out.println("SQLException has risen.");
        }
    }

    public static void insertPhone (Connection connection) throws SQLException {
        try {
            Scanner obj = new Scanner(System.in);
            System.out.println("Enter CS Staff Account Name: ");
            String accountName = obj.nextLine();
            System.out.println("Enter the new Phone Extension: ");
            int phoneext = obj.nextInt();

            PreparedStatement csphone = connection.prepareStatement("insert into PhoneExtensions values(?, ?)");
            csphone.setString(1, accountName);
            csphone.setInt(2, phoneext);
            csphone.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("SQLException has risen.");
        }
    }

}


