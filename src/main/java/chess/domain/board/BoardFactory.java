package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private static final Map<Position, Piece> BOARD = new HashMap<>();

    static {
        setUpBlank();
    }

    private static void setUpBlank() {
        for (int row = Position.START_INDEX; row <= Position.END_INDEX; row++) {
            for (int col = Position.START_INDEX; col <= Position.END_INDEX; col++) {
                String position = (char)(col + 96) + String.valueOf(row);
                BOARD.put(Position.of(position), Piece.of(PieceType.BLANK));
            }
        }
    }

    private BoardFactory() {
    }

    public static Board createBoard() {
        return new Board(BOARD);
    }
}
