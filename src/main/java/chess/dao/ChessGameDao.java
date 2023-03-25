package chess.dao;

import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.dto.PositionStringMapper;

import java.sql.*;
import java.util.Map;

public final class ChessGameDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private final Connection connection;

    public ChessGameDao() {
        this.connection = getConnection();
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Long createGame() {
        String sql = "INSERT INTO game SET turn = 'WHITE'";
        try (var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public void saveGame(ChessGame chessGame) {
        Map<Position, Piece> board = chessGame.getBoard();
        Long gameId = chessGame.getGameId();

        board.forEach(
                (position, piece) -> {

                    String sql = "INSERT INTO board SET " +
                            "position = ?," +
                            "piece = ?," +
                            "color = ?," +
                            "game_id = ?";
                    try (var preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setString(1, PositionStringMapper.mapping(position));
                        preparedStatement.setString(2, piece.getName());
                        preparedStatement.setString(3, piece.getColor().name());
                        preparedStatement.setLong(4, gameId);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
        );
    }

    public void updateGame(ChessGame chessGame, Position source, Position target) {
        Long gameId = chessGame.getGameId();
        String turn = chessGame.getTurn();

        updateTurn(gameId, turn);

        Map<Position, Piece> board = chessGame.getBoard();
        Piece piece = board.get(target);
        String mappingSource = PositionStringMapper.mapping(source);
        String mappingTarget = PositionStringMapper.mapping(target);

        updateTarget(gameId, piece, mappingTarget);
        deleteSource(mappingSource, gameId);
    }

    private void deleteSource(final String mappingSource, final Long gameId) {
        String sql = "DELETE FROM board " +
                "WHERE position = ?" +
                "AND game_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, mappingSource);
            preparedStatement.setLong(2, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateTarget(final Long gameId, final Piece piece, final String mappingTarget) {
        String sql = "INSERT INTO board (piece, color) VALUES(?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "piece = ?," +
                "color = ? " +
                "WHERE position = ? " +
                "AND game_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, piece.getName());
            preparedStatement.setString(2, piece.getColor().name());
            preparedStatement.setString(3, piece.getName());
            preparedStatement.setString(4, piece.getColor().name());
            preparedStatement.setString(5, mappingTarget);
            preparedStatement.setLong(6, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateTurn(final Long gameId, final String turn) {
        String sql = "UPDATE game SET turn = ? WHERE game_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, turn);
            preparedStatement.setLong(2, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//
//    public ChessGame load(int gameId) {
//        String turn = findTurnById(gameId);
//        Map<Position, Piece> board = new HashMap<>();
//
//        String sql = "SELECT * FROM board WHERE game_id = ?";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, gameId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                String position = resultSet.getString("position");
//                String piece = resultSet.getString("piece");
//                String color = resultSet.getString("color");
//                Kind kind = Kind.valueOf(piece);
//                board.put(Position.from(position), kind.toPiece(color));
//            }
//
//            return ChessGame.load(new Board(board), Color.valueOf(turn));
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }

    private String findTurnById(int gameId) {
        String sql = "SELECT * FROM game WHERE game_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("turn");
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}
