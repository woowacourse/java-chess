package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import java.util.Arrays;
import java.util.List;

public class Game {

    private final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private boolean isPlaying;

    public Game() {
        board = BoardFactory.initializeBoard();
        whitePlayer = new Player(PieceColor.WHITE);
        blackPlayer = new Player(PieceColor.BLACK);
        isPlaying = true;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void command(String input) {
        if (Command.isEnd(input)) {
            isPlaying = false;
            return;
        }
    }

    public Board getBoard() {
        return board;
    }
}
