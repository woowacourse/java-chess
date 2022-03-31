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

    public static Map<Position, Piece> bishopMovableHurdleTestSetUp() {
        return Map.of(
                // Hurdle 설정
                Position.of("d", "2"), new Rook(Color.WHITE),
                Position.of("g", "2"), new Rook(Color.WHITE),
                Position.of("d", "7"), new Rook(Color.WHITE),
                Position.of("g", "7"), new Rook(Color.WHITE)
        );
    }
}
