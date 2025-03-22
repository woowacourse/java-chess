package chess.piece;


import static chess.board.InitPositions.A1;
import static chess.board.InitPositions.A2;
import static chess.board.InitPositions.A7;
import static chess.board.InitPositions.A8;
import static chess.board.InitPositions.B1;
import static chess.board.InitPositions.B2;
import static chess.board.InitPositions.B7;
import static chess.board.InitPositions.B8;
import static chess.board.InitPositions.C1;
import static chess.board.InitPositions.C2;
import static chess.board.InitPositions.C7;
import static chess.board.InitPositions.C8;
import static chess.board.InitPositions.D1;
import static chess.board.InitPositions.D2;
import static chess.board.InitPositions.D7;
import static chess.board.InitPositions.D8;
import static chess.board.InitPositions.E1;
import static chess.board.InitPositions.E2;
import static chess.board.InitPositions.E7;
import static chess.board.InitPositions.E8;
import static chess.board.InitPositions.F1;
import static chess.board.InitPositions.F2;
import static chess.board.InitPositions.F7;
import static chess.board.InitPositions.F8;
import static chess.board.InitPositions.G1;
import static chess.board.InitPositions.G2;
import static chess.board.InitPositions.G7;
import static chess.board.InitPositions.G8;
import static chess.board.InitPositions.H1;
import static chess.board.InitPositions.H2;
import static chess.board.InitPositions.H7;
import static chess.board.InitPositions.H8;
import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;
import static chess.piece.PieceType.ROOK;

import chess.board.Position;
import chess.piece.nonsliding.King;
import chess.piece.nonsliding.Knight;
import chess.piece.pawn.BlackPawn;
import chess.piece.pawn.WhitePawn;
import chess.piece.sliding.Bishop;
import chess.piece.sliding.Queen;
import chess.piece.sliding.Rook;

public enum InitPieces {
    ROOK_WHITE_LEFT(A1.position, new Rook(WHITE, A1.position)),
    KNIGHT_WHITE_LEFT(B1.position, new Knight(WHITE, B1.position)),
    BISHOP_WHITE_LEFT(C1.position, new Bishop(WHITE, C1.position)),

    QUEEN_WHITE(D1.position, new Queen(WHITE, D1.position)),
    KING_WHITE(E1.position, new King(WHITE, E1.position)),

    BISHOP_WHITE_RIGHT(F1.position, new Bishop(WHITE, F1.position)),
    KNIGHT_WHITE_RIGHT(G1.position, new Knight(WHITE, G1.position)),
    ROOK_WHITE_RIGHT(H1.position, new Rook(WHITE, H1.position)),

    PAWN_WHITE_1(A2.position, new WhitePawn(A2.position)),
    PAWN_WHITE_2(B2.position, new WhitePawn(B2.position)),
    PAWN_WHITE_3(C2.position, new WhitePawn(C2.position)),
    PAWN_WHITE_4(D2.position, new WhitePawn(D2.position)),
    PAWN_WHITE_5(E2.position, new WhitePawn(E2.position)),
    PAWN_WHITE_6(F2.position, new WhitePawn(F2.position)),
    PAWN_WHITE_7(G2.position, new WhitePawn(G2.position)),
    PAWN_WHITE_8(H2.position, new WhitePawn(H2.position)),

    ROOK_BLACK_LEFT(A8.position, new Rook(BLACK, A8.position)),
    KNIGHT_BLACK_LEFT(B8.position, new Knight(BLACK, B8.position)),
    BISHOP_BLACK_LEFT(C8.position, new Bishop(BLACK, C8.position)),

    QUEEN_BLACK(D8.position, new Queen(BLACK, D8.position)),
    KING_BLACK(E8.position, new King(BLACK, E8.position)),

    BISHOP_BLACK_RIGHT(F8.position, new Bishop(BLACK, F8.position)),
    KNIGHT_BLACK_RIGHT(G8.position, new Knight(BLACK, G8.position)),
    ROOK_BLACK_RIGHT(H8.position, new Rook(BLACK, H8.position)),

    PAWN_BLACK_1(A7.position, new BlackPawn(A7.position)),
    PAWN_BLACK_2(B7.position, new BlackPawn(B7.position)),
    PAWN_BLACK_3(C7.position, new BlackPawn(C7.position)),
    PAWN_BLACK_4(D7.position, new BlackPawn(D7.position)),
    PAWN_BLACK_5(E7.position, new BlackPawn(E7.position)),
    PAWN_BLACK_6(F7.position, new BlackPawn(F7.position)),
    PAWN_BLACK_7(G7.position, new BlackPawn(G7.position)),
    PAWN_BLACK_8(H7.position, new BlackPawn(H7.position)),
    ;

    private final Position position;
    private final Piece piece;

    InitPieces(Position postion, Piece piece) {
        this.position = postion;
        this.piece = piece;
    }

    public Position position() {
        return position;
    }

    public Piece piece() {
        return piece;
    }
}
