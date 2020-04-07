package chess.dao;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.sql.*;
import java.util.*;

public class ChessDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public long createChessGame(ChessGame chessGame) throws SQLException {
        String query = "INSERT INTO chessGameTable (turn) VALUES (?)";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        String turn = chessGame.getTurn().toString();
        pstmt.setString(1, turn);
        pstmt.executeUpdate();
        Long id = getCurrentGameId(connection);
        addBoard(id, chessGame);
        return id;
    }

    public long getCurrentGameId(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT MAX(id) as recentId FROM chessGameTable;");
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException();
        }
        return rs.getLong("recentId");
    }

    public void addBoard(long chessGameId, ChessGame chessGame) throws SQLException {
        Connection connection = getConnection();

        deleteBoard(chessGameId, connection);
        updateTurn(chessGameId, chessGame, connection);
        insertBoard(chessGameId, chessGame, connection);
        closeConnection(connection);
    }

    private void updateTurn(long chessGameId, ChessGame chessGame, Connection connection) throws SQLException {
        System.out.println(chessGame);
        String updateTurnQuery = "UPDATE chessGameTable SET turn = ? where id = ?";
        PreparedStatement updatePstmt = connection.prepareStatement(updateTurnQuery);
        updatePstmt.setString(1, chessGame.getTurn().toString());
        updatePstmt.setLong(2, chessGameId);
        updatePstmt.executeUpdate();
    }

    private void deleteBoard(final long chessGameId, final Connection connection) throws SQLException {
        String deleteBoardQuery = "DELETE FROM boardTable WHERE gameId = (?)";
        PreparedStatement deleteBoardPstmt = connection.prepareStatement(deleteBoardQuery);
        deleteBoardPstmt.setLong(1, chessGameId);
        deleteBoardPstmt.executeUpdate();
    }

    public void deleteGame(final long chessGameId) throws SQLException {
        Connection connection = getConnection();
        deleteBoard(chessGameId, connection);
        String deleteGameQuery = "DELETE FROM chessGameTable WHERE id = (?);";
        PreparedStatement deleteGamePstmt = connection.prepareStatement(deleteGameQuery);
        deleteGamePstmt.setLong(1, chessGameId);
        deleteGamePstmt.executeUpdate();
        deleteGamePstmt.close();

        String alterGameQuery = "ALTER TABLE chessGameTable AUTO_INCREMENT = ?;";
        PreparedStatement alterGamePstmt = connection.prepareStatement(alterGameQuery);
        alterGamePstmt.setInt(1, (int) chessGameId);
        alterGamePstmt.executeUpdate();
        alterGamePstmt.close();
        closeConnection(connection);
    }

    private void insertBoard(final long chessGameId, final ChessGame chessGame, final Connection connection) throws SQLException {
        Map<Position, PieceState> board = chessGame.getBoard();

        String query = "INSERT INTO boardTable values ((select id from positionTable where position = ?)," +
                " (select id from pieceTable where piece = ?), (select id from teamTable where team = ?), ?);";
        PreparedStatement pstmt;
        for (Map.Entry<Position, PieceState> entry : board.entrySet()) {
            String position = entry.getKey().getName();
            String piece = entry.getValue().getPieceType().toString();
            String team = entry.getValue().getTeam().toString();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, position);
            pstmt.setString(2, piece);
            pstmt.setString(3, team);
            pstmt.setLong(4, chessGameId);
            pstmt.executeUpdate();
        }
    }


    public ChessGame findGameById(long id) throws SQLException {
        Connection connection = getConnection();

        Map<Position, PieceState> board = getCurrentBoard(id, connection);
        Turn turn = getCurrentTurn(id, connection);
        if (turn == null) return null;
        closeConnection(connection);
        return new ChessGame(Board.of(board), turn);
    }

    private Map<Position, PieceState> getCurrentBoard(final long id, final Connection connection) throws SQLException {
        String query = "SELECT position, piece, team FROM boardTable board " +
                "inner join positionTable po on po.id=board.positionId " +
                "inner join pieceTable pi on pi.id=board.pieceId  " +
                "inner join teamTable team on team.id = board.teamId " +
                "where gameId = (?);";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        Map<Position, PieceState> board = new HashMap<>();
        while (rs.next()) {
            Position position = Position.of(rs.getString("position"));
            Team team = Team.valueOf(rs.getString("team"));
            PieceState pieceState = createPieceState(rs.getString("piece"), position, team);
            board.put(position, pieceState);
        }
        return board;
    }

    private Turn getCurrentTurn(final long id, final Connection connection) throws SQLException {
        final ResultSet rs;
        String getTurnQuery = "SELECT turn from chessGameTable where id = (?);";
        PreparedStatement pstmt2 = connection.prepareStatement(getTurnQuery);
        pstmt2.setLong(1, id);
        rs = pstmt2.executeQuery();
        if (!rs.next()) {
            return null;
        }
        Turn turn = Turn.from(Team.valueOf(rs.getString("turn")));
        return turn;
    }

    private PieceState createPieceState(final String piece, final Position position, final Team team) {
        PieceType type = PieceType.valueOf(piece);
        return type.apply(position, team);
    }

    public List<Long> getRoomId() throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT id FROM chessGameTable";
        List<Long> roomId = new ArrayList<>();
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            roomId.add(rs.getLong("id"));
        }
        if (rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        pstmt.close();
        closeConnection(connection);
        return Collections.unmodifiableList(roomId);
    }
}