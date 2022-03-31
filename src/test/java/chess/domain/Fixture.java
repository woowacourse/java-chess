package chess.domain;

import chess.domain.piece.attribute.Color;
import chess.domain.refactorPiece.Piece;
import chess.domain.refactorPiece.Rook;
import chess.domain.refactorPosition.Position;
import java.util.Map;

public class Fixture {

    public static Map<Position, Piece> rookMovableHurdleTestSetUp() {
        return Map.of(
                Position.of("e", "1"), new Rook(Color.WHITE),
                Position.of("a", "4"), new Rook(Color.WHITE),
                Position.of("h", "4"), new Rook(Color.WHITE)
        );
    }
}
