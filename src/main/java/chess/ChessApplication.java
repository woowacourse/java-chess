package chess;

import chess.controller.ChessController;
import chess.dao.ChessMovementDao;
import chess.dao.JdbcChessMovementDao;
import chess.model.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ChessApplication {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final InputView inputView = new InputView(scanner);
        final OutputView outputView = new OutputView();
        final ChessController chessController = new ChessController(inputView, outputView);
        final ChessGame chessGame = new ChessGame();

        start(chessController, chessGame);
    }

    private static void start(final ChessController chessController, final ChessGame chessGame) {
        try (final Connection connection = ConnectionHolder.CONNECTION) {
            final ChessMovementDao chessMovementDao = new JdbcChessMovementDao(connection);

            chessController.start(chessGame, chessMovementDao);
        } catch (SQLException ignored) {

        }
    }

    private static final class ConnectionHolder {
        private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
        private static final String DATABASE = "chess"; // MySQL DATABASE 이름
        private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"; // MySQL 옵션
        private static final String USERNAME = "user"; //  MySQL 서버 아이디
        private static final String PASSWORD = "password"; // MySQL 서버 비밀번호

        private static final Connection CONNECTION;

        static {
            try {
                CONNECTION = DriverManager
                        .getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IllegalStateException("DB 접속에 실패했습니다.");
            }
        }
    }
}
