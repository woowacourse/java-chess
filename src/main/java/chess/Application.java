package chess;

import chess.boardstrategy.InitialBoardStrategy;
import chess.controller.Controller;
import chess.domain.game.ChessGame;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new ChessGame());
        controller.playChessGame(new InitialBoardStrategy());
    }
}