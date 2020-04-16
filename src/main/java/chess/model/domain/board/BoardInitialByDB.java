package chess.model.domain.board;

import chess.model.domain.piece.Piece;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardInitialByDB implements BoardInitialization {

    private final Map<BoardSquare, Piece> board;

    public BoardInitialByDB(Map<BoardSquare, Piece> board) {
        this.board = Collections.unmodifiableMap(board);
    }

    @Override
    public Map<BoardSquare, Piece> getInitialize() {
        return new HashMap<>(board);
    }
}
