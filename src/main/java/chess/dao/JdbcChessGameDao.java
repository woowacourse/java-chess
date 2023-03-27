package chess.dao;

import java.sql.*;

import chess.dao.entity.ChessGameEntity;
import chess.controller.command.Turn;
import chess.exception.NotFoundChessGameException;

public class JdbcChessGameDao implements ChessGameDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private static final JdbcChessGameDao INSTANCE = new JdbcChessGameDao();
    private static final long INITIAL_GAME_ID = 0L;

    private JdbcChessGameDao() {
    }

    public static JdbcChessGameDao getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long saveNewChessGame() {
        final String query = "INSERT INTO chess_game(turn) VALUES(?)";
        Long autoIncrementValue = 0L;
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, Turn.WHITE.getDisplayName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                autoIncrementValue = generatedKeys.getLong(1);
            }
            return autoIncrementValue;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long findRecentGameId() {
        final String query = "SELECT * FROM chess_game ORDER BY game_id desc LIMIT 1";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return INITIAL_GAME_ID;
            }
            return resultSet.getLong("game_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isExistPreviousChessGame(Long gameId) {
        final String query = "SELECT * FROM chess_game WHERE game_id = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setLong(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.isBeforeFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessGameEntity findChessGameByGameId(Long gameId) {
        final String query = "SELECT * FROM chess_game WHERE game_id = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setLong(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String turnName = resultSet.getString("turn");
                return new ChessGameEntity.Builder()
                        .id(gameId)
                        .turn(turnName)
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new NotFoundChessGameException("[ERROR] 체스 게임을 찾지 못했습니다.");
    }

    @Override
    public void updateTurn(ChessGameEntity chessGameEntity) {
        final String query = "UPDATE chess_game SET turn = ? WHERE game_id = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, chessGameEntity.getTurn());
            preparedStatement.setLong(2, chessGameEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
