import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class TransferServlet extends HttpServlet
{
    public void doPost(HttpServletRequest req,
                       HttpServletResponse res)
                       throws ServletException, IOException
    {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Transaction Result</title>");

        out.println("<style>");
        out.println("body {");
        out.println("font-family: Arial, sans-serif;");
        out.println("display: flex;");
        out.println("justify-content: center;");
        out.println("align-items: center;");
        out.println("min-height: 100vh;");
        out.println("margin: 0;");
        out.println("background: #f5f5f5;");
        out.println("}");

        out.println(".result {");
        out.println("background: white;");
        out.println("padding: 40px;");
        out.println("border-radius: 10px;");
        out.println("box-shadow: 0 4px 6px rgba(0,0,0,0.1);");
        out.println("width: 350px;");
        out.println("text-align: center;");
        out.println("}");

        out.println(".result h2 {");
        out.println("margin-bottom: 20px;");
        out.println("}");

        out.println(".result a {");
        out.println("display: inline-block;");
        out.println("margin-top: 20px;");
        out.println("padding: 10px 20px;");
        out.println("background: #333;");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("border-radius: 5px;");
        out.println("}");

        out.println(".result a:hover {");
        out.println("background: #555;");
        out.println("}");
        out.println("</style>");

        out.println("</head>");
        out.println("<body>");
        out.println("<div class='result'>");

        String from = req.getParameter("fromacc");
        String to = req.getParameter("toacc");
        double amt =
        Double.parseDouble(req.getParameter("amount"));

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
            DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/bankdb",
            "root",
            "Ahiram@2005");

            PreparedStatement ps1 =
            con.prepareStatement(
            "select balance from users where acc_no=?");

            ps1.setString(1, from);
            ResultSet rs1 = ps1.executeQuery();

            PreparedStatement ps2 =
            con.prepareStatement(
            "select balance from users where acc_no=?");

            ps2.setString(1, to);
            ResultSet rs2 = ps2.executeQuery();

            if(rs1.next() && rs2.next())
            {
                double bal = rs1.getDouble(1);

                if(bal >= amt)
                {
                    PreparedStatement ps3 =
                    con.prepareStatement(
                    "update users set balance=balance-? where acc_no=?");

                    ps3.setDouble(1, amt);
                    ps3.setString(2, from);
                    ps3.executeUpdate();

                    PreparedStatement ps4 =
                    con.prepareStatement(
                    "update users set balance=balance+? where acc_no=?");

                    ps4.setDouble(1, amt);
                    ps4.setString(2, to);
                    ps4.executeUpdate();

                    out.println("<h2>Transaction Successful</h2>");
                    out.println("<p>Amount " + amt + " transferred from " + from + " to " + to + "</p>");
                }
                else
                {
                    out.println("<h2>Insufficient Balance</h2>");
                    out.println("<p>Available Balance: " + bal + "</p>");
                }
            }
            else
            {
                out.println("<h2>Invalid Account Number</h2>");
                out.println("<p>One or both account numbers do not exist.</p>");
            }

            con.close();
        }
        catch(Exception e)
        {
            out.println("<h2>Error</h2>");
            out.println("<p>" + e + "</p>");
        }

        out.println("<a href='index.html'>Back</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}