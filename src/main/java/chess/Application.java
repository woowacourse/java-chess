package chess;

import chess.boardstrategy.InitialBoardStrategy;
import chess.controller.Controller;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new InputView(), new OutputView(), new ChessGame());
        controller.playChessGame(new InitialBoardStrategy());
    }
}