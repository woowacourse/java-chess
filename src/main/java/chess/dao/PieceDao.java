package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;

import java.sql.*;

public class PieceDao {
    public void add(Piece piece) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into pieces(id, team, name) values(?,?,?)");
        preparedStatement.setString(1, piece.getId());
        Team team = piece.getTeam();
        preparedStatement.setString(2, team.toString());
        preparedStatement.setString(3, piece.getName());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public Piece get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from pieces where id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();

        Piece piece = new Piece(Team.valueOf(rs.getString("team")), rs.getString("name"), null, null);
        piece.setId(id);

        rs.close();
        ps.close();
        c.close();

        return piece;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/java-chess", "root", "p-vibe");
    }


}
