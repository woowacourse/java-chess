package chess;

import chess.controller.Game;
import chess.domain.board.Board;
import chess.domain.ui.UserInterface;
import chess.ui.Console;

public class Application {
    public static void main(String[] args) {
        UserInterface userInterface = new Console();
        Game game = new Game(userInterface);
        try {
            Board board = game.start();
            board = game.play(board);
            game.showResult(board);
        } catch (RuntimeException e) {
            System.out.println(String.format("다음과 같은 이유로 중단합니다 - %s", e.getMessage()));
            System.exit(-1);
        }
    }
}
