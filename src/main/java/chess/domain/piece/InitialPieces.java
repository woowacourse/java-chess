package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Position;

import java.util.Map;

public enum InitialPieces {
    WHITE_ROOK1(new Piece(Color.WHITE, PieceType.ROOK, new RookMovingPattern(), 0), Position.of("a1")),
    WHITE_KNIGHT1(new Piece(Color.WHITE, PieceType.KNIGHT, new KnightMovingPattern(), 0), Position.of("b1")),
    WHITE_BISHOP1(new Piece(Color.WHITE, PieceType.BISHOP, new BishopMovingPattern(), 0), Position.of("c1")),
    WHITE_QUEEN(new Piece(Color.WHITE, PieceType.QUEEN, new QueenMovingPattern(), 0), Position.of("d1")),
    WHITE_KING(new Piece(Color.WHITE, PieceType.KING, new KingMovingPattern(), 0), Position.of("e1")),
    WHITE_BISHOP2(new Piece(Color.WHITE, PieceType.BISHOP, new BishopMovingPattern(), 0), Position.of("f1")),
    WHITE_KNIGHT2(new Piece(Color.WHITE, PieceType.KNIGHT, new KnightMovingPattern(), 0), Position.of("g1")),
    WHITE_ROOK2(new Piece(Color.WHITE, PieceType.ROOK, new RookMovingPattern(), 0), Position.of("h1")),

    WHITE_PAWN1(new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("a2")),
    WHITE_PAWN2(new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("b2")),
    WHITE_PAWN3(new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("c2")),
    WHITE_PAWN4(new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("d2")),
    WHITE_PAWN5(new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("e2")),
    WHITE_PAWN6(new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("f2")),
    WHITE_PAWN7(new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("g2")),
    WHITE_PAWN8(new Piece(Color.WHITE, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("h2")),

    BLACK_PAWN1(new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("a7")),
    BLACK_PAWN2(new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("b7")),
    BLACK_PAWN3(new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("c7")),
    BLACK_PAWN4(new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("d7")),
    BLACK_PAWN5(new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("e7")),
    BLACK_PAWN6(new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("f7")),
    BLACK_PAWN7(new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("g7")),
    BLACK_PAWN8(new Piece(Color.BLACK, PieceType.PAWN, new PawnMovingPattern(), 0), Position.of("h7")),

    BLACK_ROOK1(new Piece(Color.BLACK, PieceType.ROOK, new RookMovingPattern(), 0), Position.of("a8")),
    BLACK_KNIGHT1(new Piece(Color.BLACK, PieceType.KNIGHT, new KnightMovingPattern(), 0), Position.of("b8")),
    BLACK_BISHOP1(new Piece(Color.BLACK, PieceType.BISHOP, new BishopMovingPattern(), 0), Position.of("c8")),
    BLACK_QUEEN(new Piece(Color.BLACK, PieceType.QUEEN, new QueenMovingPattern(), 0), Position.of("d8")),
    BLACK_KING(new Piece(Color.BLACK, PieceType.KING, new KingMovingPattern(), 0), Position.of("e8")),
    BLACK_BISHOP2(new Piece(Color.BLACK, PieceType.BISHOP, new BishopMovingPattern(), 0), Position.of("f8")),
    BLACK_KNIGHT2(new Piece(Color.BLACK, PieceType.KNIGHT, new KnightMovingPattern(), 0), Position.of("g8")),
    BLACK_ROOK2(new Piece(Color.BLACK, PieceType.ROOK, new RookMovingPattern(), 0), Position.of("h8"));

    private final Piece piece;
    private final Position position;

    InitialPieces(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public void addTo(Map<Position, Piece> pieces) {
        pieces.replace(position, piece);
    }
}
