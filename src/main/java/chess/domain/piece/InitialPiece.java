package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

public enum InitialPiece {
    WHITE_PAWN1(new Pawn(Color.WHITE), new Position(PositionX.A, PositionY.RANK_2)),
    WHITE_PAWN2(new Pawn(Color.WHITE), new Position(PositionX.B, PositionY.RANK_2)),
    WHITE_PAWN3(new Pawn(Color.WHITE), new Position(PositionX.C, PositionY.RANK_2)),
    WHITE_PAWN4(new Pawn(Color.WHITE), new Position(PositionX.D, PositionY.RANK_2)),
    WHITE_PAWN5(new Pawn(Color.WHITE), new Position(PositionX.E, PositionY.RANK_2)),
    WHITE_PAWN6(new Pawn(Color.WHITE), new Position(PositionX.F, PositionY.RANK_2)),
    WHITE_PAWN7(new Pawn(Color.WHITE), new Position(PositionX.G, PositionY.RANK_2)),
    WHITE_PAWN8(new Pawn(Color.WHITE), new Position(PositionX.H, PositionY.RANK_2)),

    WHITE_ROOK1(new Rook(Color.WHITE), new Position(PositionX.A, PositionY.RANK_1)),
    WHITE_KNIGHT1(new Knight(Color.WHITE), new Position(PositionX.B, PositionY.RANK_1)),
    WHITE_BISHOP1(new Bishop(Color.WHITE), new Position(PositionX.C, PositionY.RANK_1)),
    WHITE_QUEEN(new Queen(Color.WHITE), new Position(PositionX.D, PositionY.RANK_1)),
    WHITE_KING(new King(Color.WHITE), new Position(PositionX.E, PositionY.RANK_1)),
    WHITE_BISHOP2(new Bishop(Color.WHITE), new Position(PositionX.F, PositionY.RANK_1)),
    WHITE_KNIGHT2(new Knight(Color.WHITE), new Position(PositionX.G, PositionY.RANK_1)),
    WHITE_ROOK2(new Rook(Color.WHITE), new Position(PositionX.H, PositionY.RANK_1)),

    BLACK_ROOK1(new Rook(Color.BLACK), new Position(PositionX.A, PositionY.RANK_7)),
    BLACK_KNIGHT1(new Knight(Color.BLACK), new Position(PositionX.B, PositionY.RANK_7)),
    BLACK_BISHOP1(new Bishop(Color.BLACK), new Position(PositionX.C, PositionY.RANK_7)),
    BLACK_QUEEN(new Queen(Color.BLACK), new Position(PositionX.D, PositionY.RANK_7)),
    BLACK_KING(new King(Color.BLACK), new Position(PositionX.E, PositionY.RANK_7)),
    BLACK_BISHOP2(new Bishop(Color.BLACK), new Position(PositionX.F, PositionY.RANK_7)),
    BLACK_KNIGHT2(new Knight(Color.BLACK), new Position(PositionX.G, PositionY.RANK_7)),
    BLACK_ROOK2(new Rook(Color.BLACK), new Position(PositionX.H, PositionY.RANK_7)),

    BLACK_PAWN1(new Rook(Color.BLACK), new Position(PositionX.A, PositionY.RANK_8)),
    BLACK_PAWN2(new Knight(Color.BLACK), new Position(PositionX.B, PositionY.RANK_8)),
    BLACK_PAWN3(new Bishop(Color.BLACK), new Position(PositionX.C, PositionY.RANK_8)),
    BLACK_PAWN4(new Queen(Color.BLACK), new Position(PositionX.D, PositionY.RANK_8)),
    BLACK_PAWN5(new King(Color.BLACK), new Position(PositionX.E, PositionY.RANK_8)),
    BLACK_PAWN6(new Bishop(Color.BLACK), new Position(PositionX.F, PositionY.RANK_8)),
    BLACK_PAWN7(new Knight(Color.BLACK), new Position(PositionX.G, PositionY.RANK_8)),
    BLACK_PAWN8(new Rook(Color.BLACK), new Position(PositionX.H, PositionY.RANK_8));

    private final Piece piece;
    private final Position position;

    InitialPiece(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public Piece piece() {
        return piece;
    }

    public PositionX positionX() {
        return position.getPositionX();
    }

    public PositionY positionY() {
        return position.getPositionY();
    }
}
