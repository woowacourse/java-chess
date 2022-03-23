package chess;

import java.util.HashMap;
import java.util.Map;

public final class ChessBoard {

    private static final ChessBoardPosition ROOK_INITIAL_POSITION =
            new ChessBoardPosition(ChessBoardColumn.A, ChessBoardRow.ONE);

    private Map<ChessBoardPosition, Piece> value;

    public ChessBoard() {
        this.value = new HashMap<>();
        value.put(ROOK_INITIAL_POSITION, new Rook());
        value.put(ROOK_INITIAL_POSITION.flipHorizontally(), new Rook());
        value.put(ROOK_INITIAL_POSITION.flipVertically(), new Rook());
        value.put(ROOK_INITIAL_POSITION.flipDiagonally(), new Rook());

    }

    public Map<ChessBoardPosition, Piece> getValue() {
        return value;
    }
}
