package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.Map;

public enum InitialPieces {
    WHITE_ROOK1(new Rook(Color.WHITE), Position.of("a1")),
    WHITE_KNIGHT1(new Knight(Color.WHITE), Position.of("b1")),
    WHITE_BISHOP1(new Bishop(Color.WHITE), Position.of("c1")),
    WHITE_QUEEN(new Queen(Color.WHITE), Position.of("d1")),
    WHITE_KING(new King(Color.WHITE), Position.of("e1")),
    WHITE_BISHOP2(new Bishop(Color.WHITE), Position.of("f1")),
    WHITE_KNIGHT2(new Knight(Color.WHITE), Position.of("g1")),
    WHITE_ROOK2(new Rook(Color.WHITE), Position.of("h1")),

    WHITE_PAWN1(new Pawn(Color.WHITE), Position.of("a2")),
    WHITE_PAWN2(new Pawn(Color.WHITE), Position.of("b2")),
    WHITE_PAWN3(new Pawn(Color.WHITE), Position.of("c2")),
    WHITE_PAWN4(new Pawn(Color.WHITE), Position.of("d2")),
    WHITE_PAWN5(new Pawn(Color.WHITE), Position.of("e2")),
    WHITE_PAWN6(new Pawn(Color.WHITE), Position.of("f2")),
    WHITE_PAWN7(new Pawn(Color.WHITE), Position.of("g2")),
    WHITE_PAWN8(new Pawn(Color.WHITE), Position.of("h2")),

    BLACK_PAWN1(new Pawn(Color.BLACK), Position.of("a7")),
    BLACK_PAWN2(new Pawn(Color.BLACK), Position.of("b7")),
    BLACK_PAWN3(new Pawn(Color.BLACK), Position.of("c7")),
    BLACK_PAWN4(new Pawn(Color.BLACK), Position.of("d7")),
    BLACK_PAWN5(new Pawn(Color.BLACK), Position.of("e7")),
    BLACK_PAWN6(new Pawn(Color.BLACK), Position.of("f7")),
    BLACK_PAWN7(new Pawn(Color.BLACK), Position.of("g7")),
    BLACK_PAWN8(new Pawn(Color.BLACK), Position.of("h7")),

    BLACK_ROOK1(new Rook(Color.BLACK), Position.of("a8")),
    BLACK_KNIGHT1(new Knight(Color.BLACK), Position.of("b8")),
    BLACK_BISHOP1(new Bishop(Color.BLACK), Position.of("c8")),
    BLACK_QUEEN(new Queen(Color.BLACK), Position.of("d8")),
    BLACK_KING(new King(Color.BLACK), Position.of("e8")),
    BLACK_BISHOP2(new Bishop(Color.BLACK), Position.of("f8")),
    BLACK_KNIGHT2(new Knight(Color.BLACK), Position.of("g8")),
    BLACK_ROOK2(new Rook(Color.BLACK), Position.of("h8"));

    private final Piece piece;
    private final Position position;

    InitialPieces(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public void addTo(Map<Position, Piece> pieces){
        pieces.replace(position, piece);
    }
}
