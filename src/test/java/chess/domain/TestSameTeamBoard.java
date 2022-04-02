package chess.domain;

import static chess.domain.TestUtils.*;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.Square;

public class TestSameTeamBoard implements BoardGenerator {
    @Override
    public Map<Square, Piece> generate() {
        return Map.of(new Square("c3"), WHITE_QUEEN, new Square("d4"), WHITE_QUEEN);
    }
}
