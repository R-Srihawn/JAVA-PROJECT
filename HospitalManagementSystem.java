
import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hospital_db",
                "root",
                "03152728"
            );

            while (true) {
                System.out.println("\n--- HOSPITAL MANAGEMENT ---");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. Search Patient");
                System.out.println("4. Delete Patient");
                System.out.println("5. Exit");

                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter disease: ");
                    String disease = sc.nextLine();

                    System.out.print("Enter doctor name: ");
                    String doctor = sc.nextLine();

                    String sql = "INSERT INTO patients(name,age,disease,doctor) VALUES(?,?,?,?)";

                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, name);
                    ps.setInt(2, age);
                    ps.setString(3, disease);
                    ps.setString(4, doctor);

                    ps.executeUpdate();
                    System.out.println("Patient Added Successfully!");
                    ps.close();

                } else if (choice == 2) {

                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM patients");

                    while (rs.next()) {
                        System.out.println(
                            rs.getInt("id") + " " +
                            rs.getString("name") + " " +
                            rs.getInt("age") + " " +
                            rs.getString("disease") + " " +
                            rs.getString("doctor")
                        );
                    }

                    rs.close();
                    st.close();

                } else if (choice == 3) {

                    System.out.print("Enter patient ID: ");
                    int id = sc.nextInt();

                    PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM patients WHERE id=?"
                    );

                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println("Age: " + rs.getInt("age"));
                        System.out.println("Disease: " + rs.getString("disease"));
                        System.out.println("Doctor: " + rs.getString("doctor"));
                    } else {
                        System.out.println("Patient Not Found!");
                    }

                    rs.close();
                    ps.close();

                } else if (choice == 4) {

                    System.out.print("Enter patient ID: ");
                    int id = sc.nextInt();

                    PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM patients WHERE id=?"
                    );

                    ps.setInt(1, id);

                    int result = ps.executeUpdate();

                    if (result > 0)
                        System.out.println("Patient Deleted!");
                    else
                        System.out.println("Patient Not Found!");

                    ps.close();

                } else if (choice == 5) {
                    con.close();
                    sc.close();
                    System.out.println("Thank You!");
                    break;

                } else {
                    System.out.println("Invalid Choice!");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
