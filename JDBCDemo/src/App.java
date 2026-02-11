import java.sql.*;
import java.util.Scanner;

public class App {
    static String tableName;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        MyDriver driver = new MyDriver();
        driver.connection();
        System.out.println("\nConnection Established.");

        while (true) {
            System.out.println("\n1. Create Table / Change Table");
            System.out.println("2. Show Table Details");
            System.out.println("3. Insert");
            System.out.println("4. Delete");
            System.out.println("5. Update");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            switch (Integer.valueOf(sc.nextLine().trim())) {
                case 1: {
                    System.out.print("Enter the table name: ");
                    tableName = sc.nextLine().trim();
                    driver.createTable(tableName);
                    System.out.println("Table '" + tableName + "' created/selected!");
                }
                break;
                case 2: {
                    System.out.print("Enter the table name: ");
                    driver.selectAll(sc.nextLine().trim());
                }
                break;
                case 3: {
                    System.out.print("Enter roll no: ");
                    int rollNo = Integer.valueOf(sc.nextLine().trim());
                    System.out.print("Enter name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter house: ");
                    String house = sc.nextLine().trim();
                    Student s = new Student(rollNo, name, house);
                    driver.insert(tableName, s);
                    System.out.println("Record inserted!");
                }
                break;
                case 4: {
                    System.out.print("Enter roll no to delete: ");
                    int rollNo = Integer.valueOf(sc.nextLine().trim());
                    driver.delete(tableName, rollNo);
                    System.out.println("Record deleted!");
                }
                break;
                case 5: {
                    System.out.print("Enter roll no to update: ");
                    int r = Integer.valueOf(sc.nextLine().trim());
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine().trim();
                    driver.update(tableName, r, name);
                    System.out.println("Record updated!");
                }
                break;
                default: {
                    driver.close();
                    System.exit(0);
                }
            }
        }
    }
}

class MyDriver {
    final String url = "jdbc:mysql://localhost:3306/hogwarts";
    final String userName = "root";
    final String password = "Ahiram@2005";
    Connection con;

    public void connection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, userName, password);
    }

    public void createTable(String tableName) throws Exception {
        String createTable = "CREATE TABLE IF NOT EXISTS `" + tableName + "`(" +
                "`id` INT PRIMARY KEY, " +
                "`name` VARCHAR(50) NOT NULL, " +
                "`house` VARCHAR(20) NOT NULL);";
        Statement st = con.createStatement();
        st.executeUpdate(createTable);
    }

    public void selectAll(String tableName) throws Exception {
        String select = "SELECT * FROM `" + tableName + "`";
        Statement st = con.createStatement();
        try {
            ResultSet rs = st.executeQuery(select);
            System.out.println("\n*******************************");
            System.out.println("ID\t|\tName\t\t|\tHouse");
            System.out.println("*******************************");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t|\t" + rs.getString(2) + "\t\t|\t" + rs.getString(3));
            }
            System.out.println("*******************************\n");
        } catch (Exception ex) {
            System.out.println("\nError: " + ex.getMessage() + "\n");
        }
    }

    public void insert(String tableName, Student s) throws Exception {
        String insert = "INSERT INTO `" + tableName + "` VALUES(?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(insert);
        pst.setInt(1, s.rollNo);
        pst.setString(2, s.name);
        pst.setString(3, s.house);
        try {
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void delete(String tableName, int rollNo) throws Exception {
        String delete = "DELETE FROM `" + tableName + "` WHERE id = " + rollNo;
        Statement st = con.createStatement();
        try {
            st.executeUpdate(delete);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
    }

    public void update(String tableName, int rollNo, String name) throws Exception {
        String update = "UPDATE `" + tableName + "` SET name = '" + name + "' WHERE id = " + rollNo;
        Statement st = con.createStatement();
        try {
            st.executeUpdate(update);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void close() throws Exception {
        if (con != null) {
            con.close();
            System.out.println("Connection closed.");
        }
    }
}

class Student {
    int rollNo;
    String name;
    String house;

    Student(int rollNo, String name, String house) {
        this.rollNo = rollNo;
        this.name = name;
        this.house = house;
    }
}