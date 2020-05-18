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
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = connectionGetter.get();
            ps = c.prepareStatement(
                    "insert into pieces(id, team, name) values(?,?,?)");
            ps.setString(1, piece.getId());
            Team team = piece.getTeam();
            ps.setString(2, team.toString());
            ps.setString(3, piece.getName());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }

        }
    }

    public Piece get(String id) throws ClassNotFoundException, SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = connectionGetter.get();
            ps = c.prepareStatement(
                    "select * from pieces where id = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            rs.next();
            Piece piece = new Piece(Team.valueOf(rs.getString("team")), rs.getString("name"), null, null);
            piece.setId(id);
            return piece;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }


        }
    }


}
