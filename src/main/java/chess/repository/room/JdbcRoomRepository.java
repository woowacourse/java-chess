package chess.repository.room;

import chess.domain.board.Board;
import chess.domain.game.Room;
import chess.domain.gamestate.State;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import chess.repository.ConnectionUtil;
import chess.utils.BoardUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcRoomRepository implements RoomRepository {

    private final ConnectionUtil connectionUtil;

    public JdbcRoomRepository() {
        this.connectionUtil = new ConnectionUtil();
    }

    @Override
    public long insert(Room room) throws SQLException {
        int nameIdx = 1;
        int stateIdx = 2;
        int currentTeamIdx = 3;

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "INSERT INTO rooms (name, state, currentteam) VALUES (?, ?, ?)";
            conn = connectionUtil.getConnection();
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(nameIdx, room.getName());
            ps.setString(stateIdx, room.getState().getValue());
            ps.setString(currentTeamIdx, room.getCurrentTeam().getValue());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
            throw new IllegalArgumentException("[ERROR] insert - 해당 정보로 방을 만들 수 없습니다.");
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
    public void update(Room room) throws SQLException {
        int stateIdx = 1;
        int currentTeamIdx = 2;
        int idIdx = 3;

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "UPDATE rooms SET state = ?, currentteam = ? WHERE id = ?";
            conn = connectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(stateIdx, room.getState().getValue());
            ps.setString(currentTeamIdx, room.getCurrentTeam().getValue());
            ps.setLong(idIdx, room.getId());
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
    public Room findRoomById(long roomId) throws SQLException {
        Connection conn = null;
        Statement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM rooms LEFT OUTER JOIN pieces ON pieces.roomid = rooms.id WHERE rooms.id = " + roomId;
            conn = connectionUtil.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(query);

            rs.last();
            int size = rs.getRow();
            if (size == 0) {
                throw new IllegalArgumentException("[ERROR] 존재하지 않는 방입니다.");
            }
            if (size == 1) {
                long id = rs.getLong("rooms.id");
                String name = rs.getString("name");
                String state = rs.getString("state");
                String currentTeam = rs.getString("currentteam");
                return new Room(id, name, State.generateState(state, BoardUtil.generateInitialBoard()), Team.of(currentTeam));
            }

            List<Piece> pieces = new ArrayList<>();
            rs.first();
            long id = rs.getLong("rooms.id");
            String name = rs.getString("name");
            String state = rs.getString("state");
            String currentTeam = rs.getString("currentteam");
            rs.beforeFirst();
            while (rs.next()) {
                long pieceId = rs.getLong("pieces.id");
                long roomid = rs.getLong("pieces.roomid");
                char signature = rs.getString("signature").charAt(0);
                String team = rs.getString("team");
                String location = rs.getString("location");
                pieces.add(Piece.generatePiece(pieceId, roomid, signature, team, location));
            }
            Board board = Board.of(pieces);
            return new Room(id, name, State.generateState(state, board), Team.of(currentTeam));
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
    public Room findRoomByRoomName(String roomName) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM rooms LEFT OUTER JOIN pieces ON pieces.roomid = rooms.id WHERE rooms.name = ?";
            conn = connectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, roomName);
            rs = ps.executeQuery();

            rs.last();
            int size = rs.getRow();
            if (size == 0) {
                throw new IllegalArgumentException("[ERROR] 존재하지 않는 방입니다.");
            }
            if (size == 1) {
                long id = rs.getLong("rooms.id");
                String name = rs.getString("name");
                String state = rs.getString("state");
                String currentTeam = rs.getString("currentteam");
                return new Room(id, name, State.generateState(state, BoardUtil.generateInitialBoard()), Team.of(currentTeam));
            }

            List<Piece> pieces = new ArrayList<>();
            rs.first();
            long id = rs.getLong("rooms.id");
            String name = rs.getString("name");
            String state = rs.getString("state");
            String currentTeam = rs.getString("currentteam");
            rs.beforeFirst();
            while (rs.next()) {
                long pieceId = rs.getLong("pieces.id");
                long roomid = rs.getLong("pieces.roomid");
                char signature = rs.getString("signature").charAt(0);
                String team = rs.getString("team");
                String location = rs.getString("location");
                pieces.add(Piece.generatePiece(pieceId, roomid, signature, team, location));
            }
            return new Room(id, name, State.generateState(state, Board.of(pieces)), Team.of(currentTeam));
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
    public boolean isExistRoomName(String roomName) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM rooms WHERE name = ?";
            conn = connectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, roomName);
            rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true;
                }
                return false;
            }
            throw new IllegalArgumentException("[ERROR] SQL 에러");
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
    public void deleteAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM rooms";
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
            String query = "SELECT COUNT(*) FROM rooms";
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
}
