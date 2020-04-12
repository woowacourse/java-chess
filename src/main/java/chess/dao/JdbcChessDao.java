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

public class JdbcChessDao implements ChessDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        Connection con = null;

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
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
            PreparedStatement prepareStatement = con.prepareStatement(query);
            String turn = chessGame.getTurn().toString();
            prepareStatement.setString(1, turn);
            prepareStatement.executeUpdate();
            String getIdQuery = "SELECT MAX(id) as recentId FROM chessGameTable";
            PreparedStatement getIdPrepareStatement = con.prepareStatement(getIdQuery);
            ResultSet resultSet = getIdPrepareStatement.executeQuery();
            if (!resultSet.next()) {
                return 0;
            }
            long id = resultSet.getLong("recentId");
            addBoard(id, chessGame);
            return id;
        }
    }

    @Override
    public void addBoard(long chessGameId, ChessGame chessGame) throws SQLException {
        try (Connection connection = getConnection()) {
            deleteBoard(chessGameId, connection);
            updateTurn(chessGameId, chessGame, connection);
            insertBoard(chessGameId, chessGame, connection);
        }
    }

    private void updateTurn(long chessGameId, ChessGame chessGame, Connection connection) throws SQLException {
        String updateTurnQuery = "UPDATE chessGameTable SET turn = ? where id = ?";
        try (PreparedStatement updatePrepareStatement = connection.prepareStatement(updateTurnQuery)) {
            updatePrepareStatement.setString(1, chessGame.getTurn().toString());
            updatePrepareStatement.setLong(2, chessGameId);
            updatePrepareStatement.executeUpdate();
        }
    }

    private void deleteBoard(final long chessGameId, final Connection connection) throws SQLException {
        String deleteBoardQuery = "DELETE FROM boardTable WHERE gameId = (?)";
        try (PreparedStatement deleteBoardPrepareStatement = connection.prepareStatement(deleteBoardQuery)) {
            deleteBoardPrepareStatement.setLong(1, chessGameId);
            deleteBoardPrepareStatement.executeUpdate();
        }
    }

    @Override
    public void deleteGame(final long chessGameId) throws SQLException {
        String deleteGameQuery = "DELETE FROM chessGameTable WHERE id = (?);";
        String alterGameQuery = "ALTER TABLE chessGameTable AUTO_INCREMENT = ?;";
        try (Connection connection = getConnection();
             PreparedStatement deleteGamePrepareStatement = connection.prepareStatement(deleteGameQuery);
             PreparedStatement alterGamePrepareStatement = connection.prepareStatement(alterGameQuery)
        ) {
            deleteBoard(chessGameId, connection);
            deleteGamePrepareStatement.setLong(1, chessGameId);
            deleteGamePrepareStatement.executeUpdate();
            deleteGamePrepareStatement.close();
            alterGamePrepareStatement.setInt(1, (int) chessGameId);
            alterGamePrepareStatement.executeUpdate();
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
            try (PreparedStatement prepareStatement = connection.prepareStatement(query)) {
                prepareStatement.setString(1, position);
                prepareStatement.setString(2, piece);
                prepareStatement.setString(3, team);
                prepareStatement.setLong(4, chessGameId);
                prepareStatement.executeUpdate();
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
        }
    }

    private Map<Position, PieceState> getCurrentBoard(final long id, final Connection connection) throws SQLException {
        String query = "SELECT position, piece, team FROM boardTable board " +
                "inner join positionTable po on po.id=board.positionId " +
                "inner join pieceTable pi on pi.id=board.pieceId  " +
                "inner join teamTable team on team.id = board.teamId " +
                "where gameId = (?);";
        try (PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setLong(1, id);
            ResultSet rs = prepareStatement.executeQuery();
            Map<Position, PieceState> board = new HashMap<>();
            while (rs.next()) {
                Position position = Position.of(rs.getString("position"));
                Player team = Player.valueOf(rs.getString("team"));
                PieceState pieceState = createPieceState(rs.getString("piece"), position, team);
                board.put(position, pieceState);
            }
            return board;
        }
    }

    private Turn getCurrentTurn(final long id, final Connection connection) throws SQLException {
        String getTurnQuery = "SELECT turn from chessGameTable where id = (?);";
        try (PreparedStatement prepareStatement = connection.prepareStatement(getTurnQuery)) {
            prepareStatement.setLong(1, id);
            ResultSet rs = prepareStatement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return Turn.from(Player.valueOf(rs.getString("turn")));
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
             PreparedStatement prepareStatement = connection.prepareStatement(query);
             ResultSet rs = prepareStatement.executeQuery()
        ) {
            while (rs.next()) {
                roomId.add(rs.getLong("id"));
            }
            return Collections.unmodifiableList(roomId);
        }
    }

    @Override
    public Map<Long, ChessGame> getDatabase() throws SQLException {
        List<Long> rooms = getRoomId();
        Map<Long, ChessGame> database = new HashMap<>();

        for (Long room : rooms) {
            database.put(room, findGameById(room));
        }
        return database;
    }
}