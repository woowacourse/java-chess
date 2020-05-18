package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;

import java.sql.*;

public class PieceDao {

    private final ConnectionGetter connectionGetter;

    public PieceDao(ConnectionGetter connectionGetter) {
        this.connectionGetter = connectionGetter;
    }


    public void add(Piece piece) throws ClassNotFoundException, SQLException {
        Connection connection = connectionGetter.get();
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
        Connection c = connectionGetter.get();

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


}
