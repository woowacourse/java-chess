package techcourse.fp.chess;

import java.util.Scanner;
import techcourse.fp.chess.controller.ChessController;
import techcourse.fp.chess.dao.MysqlChessGameDao;
import techcourse.fp.chess.dao.MysqlPieceDao;
import techcourse.fp.chess.service.ChessGameService;
import techcourse.fp.chess.view.InputView;
import techcourse.fp.chess.view.OutputView;

public final class MainApp {

    public static void main(String[] args) {
        final ChessController chessController = new ChessController(
                new InputView(new Scanner(System.in)), new OutputView(),
                new ChessGameService(new MysqlChessGameDao(), new MysqlPieceDao()));

        chessController.run();
    }
}
