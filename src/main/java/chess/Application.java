package chess;

import chess.boardstrategy.InitialBoardStrategy;
import chess.controller.Controller;
import chess.dao.ChessGameDao;
import chess.dao.MoveDao;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessGameService chessGameService = new ChessGameService(new ChessGameDao(), new MoveDao(), new InitialBoardStrategy());
        Controller controller = new Controller(new InputView(), new OutputView(), chessGameService);
        controller.run();
    }
}