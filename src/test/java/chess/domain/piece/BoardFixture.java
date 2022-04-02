package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import java.util.HashMap;
import java.util.Map;

public class BoardFixture {
    public static Board of(Position position, Piece piece) {
        final Map<Position, Piece> testBoard = new HashMap<>();
        testBoard.put(position, piece);
        return BoardFactory.newInstance(testBoard);
    }

    public static Board of(Position position1, Piece piece1, Position position2, Piece piece2) {
        final Map<Position, Piece> testBoard = new HashMap<>();
        testBoard.put(position1, piece1);
        testBoard.put(position2, piece2);
        return BoardFactory.newInstance(testBoard);
    }
}
