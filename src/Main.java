import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "123";
    static final String QUERY = "SELECT * FROM users";
    static final String DELETE = "DELETE FROM users WHERE id = ?";
    static final String INSERT = "INSERT INTO public.users(\n" +
            "\tid, login, fullname, email, age, gender)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?)";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);

            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id") + " ");
                System.out.print("Age: " + rs.getInt("age") + " ");
                System.out.print("Login: " + rs.getString("login") + " ");
                System.out.print("Fullname: " + rs.getString("fullname") + " ");
                System.out.print("Email: " + rs.getString("email") + " ");
                System.out.println("Gender: " + rs.getString("gender") + " ");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //DELETE
        int i = scanner.nextInt();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(DELETE)) {
            stmt.setInt(1, i);
            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        // INSERT
        System.out.println("ID");
        int id = scanner.nextInt();
        System.out.println("age");
        int age = scanner.nextInt();
        System.out.println("login");
        String login = scanner.next();
        System.out.println("gender");
        String gender = scanner.next();
        System.out.println("fullname");
        String fullname = scanner.next();
        System.out.println("email");
        String email = scanner.next();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(INSERT)) {

            stmt.setInt(1, id);
            stmt.setInt(5, age);
            stmt.setString(2, login);
            stmt.setString(3, fullname);
            stmt.setString(4, email);
            stmt.setString(6, gender);
            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}