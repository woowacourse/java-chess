package chess.console;

import chess.domain.board.generator.BasicBoardGenerator;
import chess.domain.board.generator.BoardGenerator;

public class ConsoleApplication {
    public static void main(String[] args) {
        BoardGenerator boardGenerator = new BasicBoardGenerator();
        ChessGame chessGame = new ChessGame();
        chessGame.play(boardGenerator);
    }
}
