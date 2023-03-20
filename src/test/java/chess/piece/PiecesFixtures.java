package chess.piece;

import static chess.PositionFixtures.A1;
import static chess.PositionFixtures.A2;
import static chess.PositionFixtures.A3;
import static chess.PositionFixtures.A8;
import static chess.PositionFixtures.B1;
import static chess.PositionFixtures.B2;
import static chess.PositionFixtures.B3;
import static chess.PositionFixtures.B4;
import static chess.PositionFixtures.B5;
import static chess.PositionFixtures.B6;
import static chess.PositionFixtures.C1;
import static chess.PositionFixtures.C2;
import static chess.PositionFixtures.C3;
import static chess.PositionFixtures.D3;
import static chess.PositionFixtures.D4;
import static chess.PositionFixtures.E5;
import static chess.PositionFixtures.G1;
import static chess.PositionFixtures.G5;
import static chess.PositionFixtures.H1;
import static chess.PositionFixtures.H2;
import static chess.PositionFixtures.H8;
import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.piece.nonsliding.King;
import chess.piece.nonsliding.Knight;
import chess.piece.sliding.Bishop;
import chess.piece.sliding.Queen;
import chess.piece.sliding.Rook;

public final class PiecesFixtures {

    public static final Piece KING_WHITE_A1 = new King(WHITE, A1);
    public static final Piece KING_WHITE_A2 = new King(WHITE, A2);
    public static final Piece KING_WHITE_A8 = new King(WHITE, A8);
    public static final Piece KING_WHITE_B2 = new King(WHITE, B2);
    public static final Piece KING_WHITE_C3 = new King(WHITE, C3);
    public static final Piece KING_WHITE_H1 = new King(WHITE, H1);
    public static final Piece KING_WHITE_H8 = new King(WHITE, H8);
    public static final Piece KING_BLACK_B1 = new King(BLACK, B1);
    public static final Piece KING_BLACK_B2 = new King(BLACK, B2);
    public static final Piece KING_BLACK_H2 = new King(BLACK, H2);

    public static final Piece KNIGHT_WHITE_A1 = new Knight(WHITE, A1);
    public static final Piece KNIGHT_WHITE_A2 = new Knight(WHITE, A2);
    public static final Piece KNIGHT_WHITE_B3 = new Knight(WHITE, B3);
    public static final Piece KNIGHT_WHITE_B5 = new Knight(WHITE, B5);
    public static final Piece KNIGHT_WHITE_B6 = new Knight(WHITE, B6);
    public static final Piece KNIGHT_WHITE_C3 = new Knight(WHITE, C3);
    public static final Piece KNIGHT_WHITE_D3 = new Knight(WHITE, D3);
    public static final Piece KNIGHT_WHITE_E5 = new Knight(WHITE, E5);
    public static final Piece KNIGHT_BLACK_B3 = new Knight(BLACK, B3);
    public static final Piece KNIGHT_BLACK_B4 = new Knight(BLACK, B4);
    public static final Piece KNIGHT_BLACK_C2 = new Knight(BLACK, C2);

    public static final Piece BISHOP_WHITE_C1 = new Bishop(WHITE, C1);
    public static final Piece BISHOP_WHITE_D4 = new Bishop(WHITE, D4);
    public static final Piece BISHOP_WHITE_G1 = new Bishop(WHITE, G1);
    public static final Piece BISHOP_WHITE_G5 = new Bishop(WHITE, G5);

    public static final Piece ROOK_WHITE_A1 = new Rook(WHITE, A1);
    public static final Piece ROOK_WHITE_A3 = new Rook(WHITE, A3);
    public static final Piece ROOK_WHITE_B2 = new Rook(WHITE, B2);

    public static final Piece QUEEN_WHITE_B2 = new Queen(WHITE, B2);

    private PiecesFixtures() {
    }
}
