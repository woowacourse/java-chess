package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import java.util.Arrays;
import java.util.List;

public class Game {

    private final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private PieceColor currentColor;
    private boolean isPlaying;

    public Game() {
        board = BoardFactory.initializeBoard();
        whitePlayer = new Player(PieceColor.WHITE);
        blackPlayer = new Player(PieceColor.BLACK);
        currentColor = PieceColor.WHITE;
        isPlaying = true;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void command(String input) {
        List<String> values = Arrays.asList(input.split(" "));
        if (Command.isEnd(values.get(0))) {
            isPlaying = false;
            return;
        }
        board.move(values);
        currentColor = currentColor.reversed();
    }


    public Board getBoard() {
        return board;
    }
}
