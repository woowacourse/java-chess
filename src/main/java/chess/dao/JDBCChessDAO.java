package chess.dao;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
import chess.domain.player.Player;
import chess.domain.position.Position;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class JDBCChessDAO implements ChessDAO {
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

    @Override
    public long createChessGame(ChessGame chessGame) throws SQLException {
        String query = "INSERT INTO chessGameTable (turn) VALUES (?)";
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            String turn = chessGame.getTurn().toString();
            pstmt.setString(1, turn);
            pstmt.executeUpdate();
            Long id = getCurrentGameId(con);
            addBoard(id, chessGame);
            return id;
        } catch (SQLException e) {
            throw e;
        }
    }

    private long getCurrentGameId(Connection connection) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT MAX(id) as recentId FROM chessGameTable");
             ResultSet rs = pstmt.executeQuery()
        ) {
            return rs.getLong("recentId");
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void addBoard(long chessGameId, ChessGame chessGame) throws SQLException {
        try (Connection connection = getConnection()) {
            deleteBoard(chessGameId, connection);
            updateTurn(chessGameId, chessGame, connection);
            insertBoard(chessGameId, chessGame, connection);
        } catch (SQLException e) {
            throw e;
        }
    }

    private void updateTurn(long chessGameId, ChessGame chessGame, Connection connection) throws SQLException {
        String updateTurnQuery = "UPDATE chessGameTable SET turn = ? where id = ?";
        try (PreparedStatement updatePstmt = connection.prepareStatement(updateTurnQuery)) {
            updatePstmt.setString(1, chessGame.getTurn().toString());
            updatePstmt.setLong(2, chessGameId);
            updatePstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    private void deleteBoard(final long chessGameId, final Connection connection) throws SQLException {
        String deleteBoardQuery = "DELETE FROM boardTable WHERE gameId = (?)";
        try (PreparedStatement deleteBoardPstmt = connection.prepareStatement(deleteBoardQuery)) {
            deleteBoardPstmt.setLong(1, chessGameId);
            deleteBoardPstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void deleteGame(final long chessGameId) throws SQLException {
        String deleteGameQuery = "DELETE FROM chessGameTable WHERE id = (?);";
        String alterGameQuery = "ALTER TABLE chessGameTable AUTO_INCREMENT = ?;";
        try (Connection connection = getConnection();
             PreparedStatement deleteGamePstmt = connection.prepareStatement(deleteGameQuery);
             PreparedStatement alterGamePstmt = connection.prepareStatement(alterGameQuery)
        ) {
            deleteBoard(chessGameId, connection);
            deleteGamePstmt.setLong(1, chessGameId);
            deleteGamePstmt.executeUpdate();
            deleteGamePstmt.close();
            alterGamePstmt.setInt(1, (int) chessGameId);
            alterGamePstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    private void insertBoard(final long chessGameId, final ChessGame chessGame,
                             final Connection connection) throws SQLException {
        Map<Position, PieceState> board = chessGame.getBoard();

        String query = "INSERT INTO boardTable values ((select id from positionTable where position = ?)," +
                " (select id from pieceTable where piece = ?), (select id from teamTable where team = ?), ?);";


        for (Map.Entry<Position, PieceState> entry : board.entrySet()) {
            String position = entry.getKey().getName();
            String piece = entry.getValue().toString();
            String team = entry.getValue().getPlayer().toString();
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, position);
                pstmt.setString(2, piece);
                pstmt.setString(3, team);
                pstmt.setLong(4, chessGameId);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    @Override
    public ChessGame findGameById(long id) throws SQLException {
        try (Connection connection = getConnection()) {
            Map<Position, PieceState> board = getCurrentBoard(id, connection);
            Turn turn = getCurrentTurn(id, connection);
            if (turn == null) return null;
            return ChessGame.load(Board.of(board), turn);
        } catch (SQLException e) {
            throw e;
        }
    }

    private Map<Position, PieceState> getCurrentBoard(final long id, final Connection connection) throws SQLException {
        String query = "SELECT position, piece, team FROM boardTable board " +
                "inner join positionTable po on po.id=board.positionId " +
                "inner join pieceTable pi on pi.id=board.pieceId  " +
                "inner join teamTable team on team.id = board.teamId " +
                "where gameId = (?);";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            Map<Position, PieceState> board = new HashMap<>();
            while (rs.next()) {
                Position position = Position.of(rs.getString("position"));
                Player team = Player.valueOf(rs.getString("team"));
                PieceState pieceState = createPieceState(rs.getString("piece"), position, team);
                board.put(position, pieceState);
            }
            return board;
        } catch (SQLException e) {
            throw e;
        }
    }

    private Turn getCurrentTurn(final long id, final Connection connection) throws SQLException {
        String getTurnQuery = "SELECT turn from chessGameTable where id = (?);";
        try (PreparedStatement pstmt2 = connection.prepareStatement(getTurnQuery)) {
            pstmt2.setLong(1, id);
            ResultSet rs = pstmt2.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Turn turn = Turn.from(Player.valueOf(rs.getString("turn")));
            return turn;
        } catch (SQLException e) {
            throw e;
        }
    }

    private PieceState createPieceState(final String piece, final Position position, final Player player) {
        PieceType type = PieceType.valueOf(piece);
        return type.apply(position, player);
    }

    @Override
    public List<Long> getRoomId() throws SQLException {
        String query = "SELECT id FROM chessGameTable";
        List<Long> roomId = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                roomId.add(rs.getLong("id"));
            }
            return Collections.unmodifiableList(roomId);
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Map<Long, ChessGame> getDatabase() throws SQLException {
        return getRoomId()
                .stream()
                .collect(Collectors.toMap(
                        id -> id,
                        id -> {
                            try {
                                return findGameById(id);
                            } catch (SQLException e) {
                                return null;
                            }
                        }));
    }
}