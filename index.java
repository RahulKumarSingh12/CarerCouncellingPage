import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Controller
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    // Define your database connection properties
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @GetMapping("/")
    public String index() {
        return "javaaa"; // Return the name of your HTML file
    }

    @GetMapping("/home")
    public String home() {
        return "javaaa"; // Return the name of your HTML file
    }

    @GetMapping("/About Us")
    public String about() {
        return "Working on it"; // Return appropriate content
    }

    @GetMapping("/signin")
    public String signin(@RequestParam(name = "firstname", required = false) String firstname,
                         @RequestParam(name = "lastname", required = false) String lastname,
                         @RequestParam(name = "email", required = false) String email,
                         @RequestParam(name = "password", required = false) String password,
                         Model model) {
        if (firstname != null) {
            // Handle database insert here
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
                String sql = "INSERT INTO tabledata (firstname, lastname, email, password) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, lastname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    model.addAttribute("Name", firstname);
                    model.addAttribute("channelName", firstname);
                    return "form";
                } else {
                    return "Error occurred during database insertion.";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error occurred during database insertion.";
            }
        } else {
            return "signin"; // Return the name of your HTML file
        }
    }

    @GetMapping("/quiz")
    public String quiz() {
        return "quiz"; // Return the name of your HTML file
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Return the name of your HTML file
    }
}
