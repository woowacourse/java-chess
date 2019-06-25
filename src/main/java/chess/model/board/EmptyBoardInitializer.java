package chess.model.board;

import chess.model.Square;
import chess.model.unit.Piece;

import java.util.HashMap;
import java.util.Map;

public class EmptyBoardInitializer implements BoardInitializer{
    @Override
    public Map<Square, Piece> initialize() {
        return new HashMap<>();
    }
}