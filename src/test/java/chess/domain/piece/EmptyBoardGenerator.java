package chess.domain.piece;

import static chess.domain.piece.Color.NO_COLOR;

import chess.domain.board.Position;
import java.util.HashMap;
import java.util.Map;

public class EmptyBoardGenerator {

    public static Map<Position, Piece> create() {
        Map<Position, Piece> boardMap = new HashMap<>();

        boardMap.put(Position.of(1, 1), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(1, 2), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(1, 3), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(1, 4), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(1, 5), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(1, 6), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(1, 7), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(1, 8), new NoPiece(NO_COLOR));

        boardMap.put(Position.of(2, 1), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(2, 2), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(2, 3), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(2, 4), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(2, 5), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(2, 6), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(2, 7), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(2, 8), new NoPiece(NO_COLOR));

        boardMap.put(Position.of(3, 1), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(3, 2), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(3, 3), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(3, 4), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(3, 5), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(3, 6), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(3, 7), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(3, 8), new NoPiece(NO_COLOR));

        boardMap.put(Position.of(4, 1), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(4, 2), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(4, 3), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(4, 4), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(4, 5), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(4, 6), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(4, 7), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(4, 8), new NoPiece(NO_COLOR));

        boardMap.put(Position.of(5, 1), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(5, 2), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(5, 3), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(5, 4), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(5, 5), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(5, 6), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(5, 7), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(5, 8), new NoPiece(NO_COLOR));

        boardMap.put(Position.of(6, 1), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(6, 2), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(6, 3), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(6, 4), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(6, 5), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(6, 6), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(6, 7), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(6, 8), new NoPiece(NO_COLOR));

        boardMap.put(Position.of(7, 1), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(7, 2), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(7, 3), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(7, 4), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(7, 5), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(7, 6), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(7, 7), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(7, 8), new NoPiece(NO_COLOR));

        boardMap.put(Position.of(8, 1), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(8, 2), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(8, 3), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(8, 4), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(8, 5), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(8, 6), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(8, 7), new NoPiece(NO_COLOR));
        boardMap.put(Position.of(8, 8), new NoPiece(NO_COLOR));

        return boardMap;
    }
}
