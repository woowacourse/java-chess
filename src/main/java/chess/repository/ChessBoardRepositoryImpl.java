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

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private final ChessBoardSpliter chessBoardSpliter = new ChessBoardSpliter();

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            return null;
        }
    }

    @Override
    public ChessBoard findChessBoard() {
        StringSpaceGeneratorConverter stringSpaceGeneratorConverter = new StringSpaceGeneratorConverter();
        StringSpaceGenerator spaceGenerator = stringSpaceGeneratorConverter.convertToObject(findInBoard("board_state"));
        TurnConverter turnConverter = new TurnConverter();
        Turn turn = turnConverter.convertToObject(findInBoard("turn"));

        return chessBoardSpliter.combine(spaceGenerator, turn);
    }

    @Override
    public ChessBoard saveChessBoard(ChessBoard chessBoard) {
        StringSpaceGenerator spaceGenerator = chessBoardSpliter.splitFirst(chessBoard);
        Turn turn = chessBoardSpliter.splitSecond(chessBoard);
        StringSpaceGeneratorConverter stringSpaceGeneratorConverter = new StringSpaceGeneratorConverter();
        TurnConverter turnConverter = new TurnConverter();

        saveInBoard("board_state",
                stringSpaceGeneratorConverter.convertToData(spaceGenerator));
        saveInBoard("turn",
                turnConverter.convertToData(turn));
        return chessBoard;
    }

    @Override
    public void deleteChessBoard() {
        saveInBoard("board_state", "");
        saveInBoard("turn", "");
    }

    public String findInBoard(String columnLabel) {
        String result = "";
        final var query = "SELECT * FROM board";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString(columnLabel);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return result;
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
}
