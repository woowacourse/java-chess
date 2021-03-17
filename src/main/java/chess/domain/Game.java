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
        List<String> values = Arrays.asList(input.split(" "));
        if (Command.isEnd(values.get(0))) {
            isPlaying = false;
            return;
        }
        Position source = Position.of(values.get(1));
        Piece piece = board.findPieceBy(source)
                .orElseThrow(()->new IllegalArgumentException("source 위치에 말이 없습니다."));
        Position target = Position.of(values.get(2));
        board.putPiece(piece, target);
    }

    public Board getBoard() {
        return board;
    }
}
