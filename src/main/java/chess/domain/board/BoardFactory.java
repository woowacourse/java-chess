package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {
    private static final List<Piece> BOARD = new ArrayList<>();

    static {
        setUpBlank();
    }

    private static void setUpBlank() {
        for (int row = Position.START_INDEX; row <= Position.END_INDEX; row++) {
            for (int col = Position.START_INDEX; col <= Position.END_INDEX; col++) {
                BOARD.add(Blank.create(new Position(col, row)));
            }
        }
    }

    private BoardFactory() {
    }

    public static Board createEmptyBoard() {
        return new Board(BOARD);
    }
}
