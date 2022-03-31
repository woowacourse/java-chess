package chess.domain;

import chess.domain.piece.attribute.Color;
import chess.domain.refactorPiece.Piece;
import chess.domain.refactorPiece.Queen;
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
                Position.of("d", "2"), new Rook(Color.WHITE),
                Position.of("g", "2"), new Rook(Color.WHITE),
                Position.of("d", "7"), new Rook(Color.WHITE),
                Position.of("g", "7"), new Rook(Color.WHITE)
        );
    }

    public static Map<Position, Piece> queenMovableHurdleTestSetUp() {
        return Map.of(
                Position.of("e", "3"), new Queen(Color.WHITE),
                Position.of("e", "1"), new Queen(Color.WHITE),
                Position.of("c", "2"), new Queen(Color.WHITE),
                Position.of("e", "2"), new Queen(Color.WHITE),
                Position.of("c", "8"), new Queen(Color.BLACK),
                Position.of("e", "8"), new Queen(Color.BLACK),
                Position.of("c", "7"), new Queen(Color.BLACK),
                Position.of("e", "7"), new Queen(Color.BLACK)
        );
    }

    public static Map<Position, Piece> whitePawnDiagonalTestSetUp() {
        return Map.of(
                Position.of("b", "3"), new Queen(Color.WHITE),
                Position.of("d", "3"), new Queen(Color.WHITE)
        );
    }

    public static Map<Position, Piece> blackPawnDiagonalTestSetUp() {
        return Map.of(
                Position.of("b", "6"), new Queen(Color.WHITE),
                Position.of("d", "6"), new Queen(Color.WHITE)
        );
    }
}
