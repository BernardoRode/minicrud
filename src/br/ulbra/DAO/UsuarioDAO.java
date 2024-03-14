package br.ulbra.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static javafx.beans.binding.Bindings.and;
import javax.swing.JOptionPane;
import static javax.swing.text.html.HTML.Tag.SELECT;

public class UsuarioDAO {

    Connection con;

    public UsuarioDAO() throws SQLException {
        con = ConnectionFactory.getConnection();
    }

    public boolean checkLogin(String login, String senha) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM tbusuario WHERE emailuso =  ? and  senhauso =  ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
        }
        ConnectionFactory.closeConnection(con, stmt, rs);

        return check;
    }
}
