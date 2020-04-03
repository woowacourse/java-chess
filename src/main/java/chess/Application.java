package chess;

import chess.controller.Game;
import chess.domain.board.Board;
import chess.domain.ui.UserInterface;
import chess.ui.Console;

public class Application {
    public static void main(String[] args) {
        UserInterface userInterface = new Console();
        Game game = new Game(userInterface);
        Board board = game.start();
        board = game.play(board);
        game.showResult(board);
    }
}
