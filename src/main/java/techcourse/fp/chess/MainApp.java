package techcourse.fp.chess;

import java.util.Scanner;
import techcourse.fp.chess.controller.ChessController;
import techcourse.fp.chess.dao.LocalMysqlChessGameDao;
import techcourse.fp.chess.view.InputView;
import techcourse.fp.chess.view.OutputView;

public final class MainApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final ChessController chessController = new ChessController(new InputView(scanner), new OutputView(), new LocalMysqlChessGameDao());
            chessController.run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
