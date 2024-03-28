package chess.repository;

import chess.domain.chessGame.ChessBoard;
import chess.domain.chessGame.Turn;
import chess.repository.exchanger.ChessBoardSpliter;
import chess.repository.exchanger.StringSpaceGenerator;
import chess.repository.exchanger.StringSpaceGeneratorConverter;
import chess.repository.exchanger.TurnConverter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessBoardRepositoryImpl implements ChessBoardRepository {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String BOARD_COLUMN = "board_state";
    private static final String TURN_COLUMN = "turn";

    private final ChessBoardSpliter chessBoardSpliter = new ChessBoardSpliter();
    private final StringSpaceGeneratorConverter stringSpaceGeneratorConverter = new StringSpaceGeneratorConverter();
    private final TurnConverter turnConverter = new TurnConverter();

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            throw new IllegalStateException("DB 연결 오류:" + e.getMessage());
        }
    }

    @Override
    public ChessBoard findChessBoard() {
        StringSpaceGenerator stringSpaceGenerator = stringSpaceGeneratorConverter.convertToObject(findInBoard("board_state"));
        Turn turn = turnConverter.convertToObject(findInBoard(TURN_COLUMN));

        return chessBoardSpliter.combine(stringSpaceGenerator, turn);
    }

    @Override
    public ChessBoard saveChessBoard(ChessBoard chessBoard) {
        StringSpaceGenerator spaceGenerator = chessBoardSpliter.splitFirst(chessBoard);
        Turn turn = chessBoardSpliter.splitSecond(chessBoard);

        boolean updated = updateIfExist(spaceGenerator, turn);
        if (!updated) {
            createInBoard(
                    stringSpaceGeneratorConverter.convertToData(spaceGenerator),
                    turnConverter.convertToData(turn));
        }

        return chessBoard;
    }

    private boolean updateIfExist(StringSpaceGenerator stringSpaceGenerator, Turn turn) {
        if (saveDateExist()) {
            saveInBoard(BOARD_COLUMN,
                    stringSpaceGeneratorConverter.convertToData(stringSpaceGenerator));
            saveInBoard(TURN_COLUMN,
                    turnConverter.convertToData(turn));
            return true;
        }
        return false;
    }

    private boolean saveDateExist() {
        try {
            findInBoard(BOARD_COLUMN);
            findInBoard(TURN_COLUMN);
            return true;
        } catch (IllegalArgumentException | IllegalStateException e) {
            return false;
        }
    }

    @Override
    public void deleteChessBoard() {
        saveInBoard(BOARD_COLUMN, "");
        saveInBoard(TURN_COLUMN, "");
    }

    public String findInBoard(String columnLabel) {
        final var query = "SELECT * FROM board WHERE board_id = 1";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(columnLabel);
            }
            throw new IllegalStateException("해당하는 값이 존재하지 않습니다");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void saveInBoard(String columnLabel, String data) {
        final var query = "UPDATE board SET " + columnLabel + " = ? WHERE board_id = 1";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, data);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void createInBoard(String boardData, String turnData) {
        final var query = "INSERT INTO board (board_id, board_state, turn) values (1, ?, ?)";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, boardData);
            statement.setString(2, turnData);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
