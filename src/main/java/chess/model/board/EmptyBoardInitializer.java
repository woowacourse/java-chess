package chess.model.board;

import chess.model.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class EmptyBoardInitializer implements BoardInitializer {
    @Override
    public Map<Square, Piece> initPieces() {
        return new HashMap<>();
    }
}
