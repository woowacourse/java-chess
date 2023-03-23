package chess.fixture;

import static chess.domain.piece.Side.BLACK;
import static chess.domain.piece.Side.WHITE;

import chess.domain.piece.*;

public class InitPiecesFixtures {

    /**
     * WHITE_PAWN
     */
    public static final Piece PAWN_WHITE_A2 = new Pawn(PositionFixtures.A2, WHITE);
    public static final Piece PAWN_WHITE_B2 = new Pawn(PositionFixtures.B2, WHITE);
    public static final Piece PAWN_WHITE_C2 = new Pawn(PositionFixtures.C2, WHITE);
    public static final Piece PAWN_WHITE_D2 = new Pawn(PositionFixtures.D2, WHITE);
    public static final Piece PAWN_WHITE_E2 = new Pawn(PositionFixtures.E2, WHITE);
    public static final Piece PAWN_WHITE_F2 = new Pawn(PositionFixtures.F2, WHITE);
    public static final Piece PAWN_WHITE_G2 = new Pawn(PositionFixtures.G2, WHITE);
    public static final Piece PAWN_WHITE_H2 = new Pawn(PositionFixtures.H2, WHITE);

    /**
     * BLACK_PAWN
     */
    public static final Piece PAWN_BLACK_A7 = new Pawn(PositionFixtures.A7, BLACK);
    public static final Piece PAWN_BLACK_B7 = new Pawn(PositionFixtures.B7, BLACK);
    public static final Piece PAWN_BLACK_C7 = new Pawn(PositionFixtures.C7, BLACK);
    public static final Piece PAWN_BLACK_D7 = new Pawn(PositionFixtures.D7, BLACK);
    public static final Piece PAWN_BLACK_E7 = new Pawn(PositionFixtures.E7, BLACK);
    public static final Piece PAWN_BLACK_F7 = new Pawn(PositionFixtures.F7, BLACK);
    public static final Piece PAWN_BLACK_G7 = new Pawn(PositionFixtures.G7, BLACK);
    public static final Piece PAWN_BLACK_H7 = new Pawn(PositionFixtures.H7, BLACK);

    /**
     * WHITE_PIECE
     */
    public static final Piece ROOK_WHITE_A1 = new Rook(PositionFixtures.A1, WHITE);
    public static final Piece ROOK_WHITE_H1 = new Rook(PositionFixtures.H1, WHITE);
    public static final Piece KNIGHT_WHITE_B1 = new Knight(PositionFixtures.B1, WHITE);
    public static final Piece KNIGHT_WHITE_G1 = new Knight(PositionFixtures.G1, WHITE);
    public static final Piece BISHOP_WHITE_C1 = new Bishop(PositionFixtures.C1, WHITE);
    public static final Piece BISHOP_WHITE_F1 = new Bishop(PositionFixtures.F1, WHITE);
    public static final Piece QUEEN_WHITE_D1 = new Queen(PositionFixtures.D1, WHITE);
    public static final Piece KING_WHITE_E1 = new King(PositionFixtures.E1, WHITE);

    /**
     * BLACK_PIECE
     */
    public static final Piece ROOK_BLACK_A8 = new Rook(PositionFixtures.A8, BLACK);
    public static final Piece ROOK_BLACK_H8 = new Rook(PositionFixtures.H8, BLACK);
    public static final Piece KNIGHT_BLACK_B8 = new Knight(PositionFixtures.B8, BLACK);
    public static final Piece KNIGHT_BLACK_G8 = new Knight(PositionFixtures.G8, BLACK);
    public static final Piece BISHOP_BLACK_C8 = new Bishop(PositionFixtures.C8, BLACK);
    public static final Piece BISHOP_BLACK_F8 = new Bishop(PositionFixtures.F8, BLACK);
    public static final Piece QUEEN_BLACK_D8 = new Queen(PositionFixtures.D8, BLACK);
    public static final Piece KING_BLACK_E8 = new King(PositionFixtures.E8, BLACK);
}
