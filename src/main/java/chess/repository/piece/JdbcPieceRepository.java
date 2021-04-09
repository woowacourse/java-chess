package chess.repository.piece;

import chess.domain.piece.Piece;
import chess.repository.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcPieceRepository implements PieceRepository {

    private final ConnectionUtil connectionUtil;

    public JdbcPieceRepository() {
        this.connectionUtil = new ConnectionUtil();
    }

    @Override
    public long insert(long roomId, Piece piece) throws SQLException {
        int roomIdIdx = 1;
        int signatureIdx = 2;
        int teamIdx = 3;
        int locationIdx = 4;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "INSERT INTO pieces (roomid, signature, team, location) VALUES (?, ?, ?, ?)";
            conn = connectionUtil.getConnection();
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(roomIdIdx, roomId);
            ps.setString(signatureIdx, String.valueOf(piece.getSignature()));
            ps.setString(teamIdx, piece.getTeam().getValue());
            ps.setString(locationIdx, String.valueOf(piece.getX()) + String.valueOf(piece.getY()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
            throw new IllegalArgumentException("[ERROR] insert - Piece정보를 DB에 저장하지 못했습니다.");
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void update(Piece piece) throws SQLException {
        int locationIdx = 1;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "UPDATE pieces SET location = ? WHERE id = ?";
            conn = connectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(locationIdx, String.valueOf(piece.getX()) + String.valueOf(piece.getY()));
            ps.setLong(2, piece.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public Piece findPieceById(long pieceId) throws SQLException {
        Connection conn = null;
        Statement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM pieces WHERE id = " + pieceId;
            conn = connectionUtil.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(query);

            if (rs.next()) {
                long id = rs.getLong("id");
                long roomId = rs.getLong("roomid");
                char signature = rs.getString("signature").charAt(0);
                String team = rs.getString("team");
                String location = rs.getString("location");
                return Piece.generatePiece(id, roomId, signature, team, location);
            }
            throw new IllegalArgumentException("[ERROR] findPieceById - DB로부터 Piece정보를 가져오지 못했습니다.");
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    @Override
    public void deletePieceById(long id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM pieces WHERE id = " + id;
            conn = connectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM pieces";
            conn = connectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public int count() throws SQLException {
        Connection conn = null;
        Statement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM pieces";
            conn = connectionUtil.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(query);

            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
            throw new IllegalArgumentException("[ERROR] count - DB에 저장된 모든 Piece의 개수를 가져오지 못했습니다.");
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    @Override
    public List<Piece> findPiecesByRoomId(long roomId) throws SQLException {
        Connection conn = null;
        Statement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM pieces WHERE roomid = " + roomId;
            conn = connectionUtil.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(query);

            List<Piece> result = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                long roomid = rs.getLong("roomid");
                char signature = rs.getString("signature").charAt(0);
                String team = rs.getString("team");
                String location = rs.getString("location");
                result.add(Piece.generatePiece(id, roomid, signature, team, location));
            }
            if (result.size() == 0) {
                throw new IllegalArgumentException("[ERROR] 아직 시작되지 않은 방입니다.");
            }
            return result;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
