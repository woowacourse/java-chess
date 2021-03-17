package chess.domain;

import chess.domain.piece.PieceColor;

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
        if ("end".equals(input)) {
            isPlaying = false;
        }
    }

    public Board getBoard() {
        return board;
    }
}
