package chess.domain.board;

import static chess.domain.TestPieces.*;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.Square;

public class TestOtherTeamBoard implements BoardGenerator {
    @Override
    public Map<Square, Piece> generate() {
        return Map.of(new Square("c3"), WHITE_QUEEN, new Square("d4"), BLACK_QUEEN);
    }
}
