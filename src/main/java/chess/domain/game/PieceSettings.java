package chess.domain.game;

import chess.domain.piece.*;

public enum PieceSettings {

    WHITE_ROOK_1_1(new Position(1, 1), new Rook(Team.WHITE)),
    WHITE_KNIGHT_1_2(new Position(1, 2), new Knight(Team.WHITE)),
    WHITE_BISHOP_1_3(new Position(1, 3), new Bishop(Team.WHITE)),
    WHITE_QUEEN_1_4(new Position(1, 4), new Queen(Team.WHITE)),
    WHITE_KING_1_5(new Position(1, 5), new King(Team.WHITE)),
    WHITE_BISHOP_1_6(new Position(1, 6), new Bishop(Team.WHITE)),
    WHITE_KNIGHT_1_7(new Position(1, 7), new Knight(Team.WHITE)),
    WHITE_ROOK_1_8(new Position(1, 8), new Rook(Team.WHITE)),
    WHITE_PAWN_2_1(new Position(2, 1), new Pawn(Team.WHITE)),
    WHITE_PAWN_2_2(new Position(2, 2), new Pawn(Team.WHITE)),
    WHITE_PAWN_2_3(new Position(2, 3), new Pawn(Team.WHITE)),
    WHITE_PAWN_2_4(new Position(2, 4), new Pawn(Team.WHITE)),
    WHITE_PAWN_2_5(new Position(2, 5), new Pawn(Team.WHITE)),
    WHITE_PAWN_2_6(new Position(2, 6), new Pawn(Team.WHITE)),
    WHITE_PAWN_2_7(new Position(2, 7), new Pawn(Team.WHITE)),
    WHITE_PAWN_2_8(new Position(2, 8), new Pawn(Team.WHITE)),
    BLACK_ROOK_8_1(new Position(8, 1), new Rook(Team.BLACK)),
    BLACK_KNIGHT_8_2(new Position(8, 2), new Knight(Team.BLACK)),
    BLACK_BISHOP_8_3(new Position(8, 3), new Bishop(Team.BLACK)),
    BLACK_QUEEN_8_4(new Position(8, 4), new Queen(Team.BLACK)),
    BLACK_KING_8_5(new Position(8, 5), new King(Team.BLACK)),
    BLACK_BISHOP_8_6(new Position(8, 6), new Bishop(Team.BLACK)),
    BLACK_KNIGHT_8_7(new Position(8, 7), new Knight(Team.BLACK)),
    BLACK_ROOK_8_8(new Position(8, 8), new Rook(Team.BLACK)),
    BLACK_PAWN_7_1(new Position(7, 1), new Pawn(Team.BLACK)),
    BLACK_PAWN_7_2(new Position(7, 2), new Pawn(Team.BLACK)),
    BLACK_PAWN_7_3(new Position(7, 3), new Pawn(Team.BLACK)),
    BLACK_PAWN_7_4(new Position(7, 4), new Pawn(Team.BLACK)),
    BLACK_PAWN_7_5(new Position(7, 5), new Pawn(Team.BLACK)),
    BLACK_PAWN_7_6(new Position(7, 6), new Pawn(Team.BLACK)),
    BLACK_PAWN_7_7(new Position(7, 7), new Pawn(Team.BLACK)),
    BLACK_PAWN_7_8(new Position(7, 8), new Pawn(Team.BLACK));

    private final Position position;
    private final Piece piece;

    PieceSettings(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }
}
