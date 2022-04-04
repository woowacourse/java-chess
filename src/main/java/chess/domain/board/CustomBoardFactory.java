package chess.domain.board;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class CustomBoardFactory implements BoardFactory {

    private static final int INITIAL_CAPACITY = 64;

    private final Map<Position, Piece> pieces;

    public CustomBoardFactory(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    @Override
    public Map<Position, Piece> create() {
        final Map<Position, Piece> board = initializeBoard();
        for (Position position : pieces.keySet()) {
            board.put(position, pieces.get(position));
        }
        return board;
    }

    private Map<Position, Piece> initializeBoard() {
        final Map<Position, Piece> board = new HashMap<>(INITIAL_CAPACITY);
        for (Position position : Position.toPieces()) {
            board.put(position, new EmptyPiece());
        }
        return board;
    }
}
