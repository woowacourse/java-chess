package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ChessInitialSetting {
    WHITE_ROOK_LEFT(BoardSquare.of("a1"), Rook.getPieceInstance(Color.WHITE)),
    WHITE_ROOK_RIGHT(BoardSquare.of("h1"), Rook.getPieceInstance(Color.WHITE)),
    BLACK_ROOK_LEFT(BoardSquare.of("h8"), Rook.getPieceInstance(Color.BLACK)),
    BLACK_ROOK_RIGHT(BoardSquare.of("a8"), Rook.getPieceInstance(Color.BLACK)),
    WHITE_KNIGHT_LEFT(BoardSquare.of("b1"), Knight.getPieceInstance(Color.WHITE)),
    WHITE_KNIGHT_RIGHT(BoardSquare.of("g1"), Knight.getPieceInstance(Color.WHITE)),
    BLACK_KNIGHT_LEFT(BoardSquare.of("g8"), Knight.getPieceInstance(Color.BLACK)),
    BLACK_KNIGHT_RIGHT(BoardSquare.of("b8"), Knight.getPieceInstance(Color.BLACK)),
    WHITE_BISHOP_LEFT(BoardSquare.of("c1"), Bishop.getPieceInstance(Color.WHITE)),
    WHITE_BISHOP_RIGHT(BoardSquare.of("f1"), Bishop.getPieceInstance(Color.WHITE)),
    BLACK_BISHOP_LEFT(BoardSquare.of("f8"), Bishop.getPieceInstance(Color.BLACK)),
    BLACK_BISHOP_RIGHT(BoardSquare.of("c8"), Bishop.getPieceInstance(Color.BLACK)),
    WHITE_QUEEN(BoardSquare.of("d1"), Queen.getPieceInstance(Color.WHITE)),
    BLACK_QUEEN(BoardSquare.of("e8"), Queen.getPieceInstance(Color.BLACK)),
    WHITE_KING(BoardSquare.of("e1"), King.getPieceInstance(Color.WHITE)),
    BLACK_KING(BoardSquare.of("d8"), King.getPieceInstance(Color.BLACK)),
    WHITE_PAWN_ONE(BoardSquare.of("a2"), Pawn.getPieceInstance(Color.WHITE)),
    WHITE_PAWN_TWO(BoardSquare.of("b2"), Pawn.getPieceInstance(Color.WHITE)),
    WHITE_PAWN_THREE(BoardSquare.of("c2"), Pawn.getPieceInstance(Color.WHITE)),
    WHITE_PAWN_FOUR(BoardSquare.of("d2"), Pawn.getPieceInstance(Color.WHITE)),
    WHITE_PAWN_FIVE(BoardSquare.of("e2"), Pawn.getPieceInstance(Color.WHITE)),
    WHITE_PAWN_SIX(BoardSquare.of("f2"), Pawn.getPieceInstance(Color.WHITE)),
    WHITE_PAWN_SEVEN(BoardSquare.of("g2"), Pawn.getPieceInstance(Color.WHITE)),
    WHITE_PAWN_EIGHT(BoardSquare.of("h2"), Pawn.getPieceInstance(Color.WHITE)),
    BLACK_PAWN_ONE(BoardSquare.of("h7"), Pawn.getPieceInstance(Color.BLACK)),
    BLACK_PAWN_TWO(BoardSquare.of("g7"), Pawn.getPieceInstance(Color.BLACK)),
    BLACK_PAWN_THREE(BoardSquare.of("f7"), Pawn.getPieceInstance(Color.BLACK)),
    BLACK_PAWN_FOUR(BoardSquare.of("e7"), Pawn.getPieceInstance(Color.BLACK)),
    BLACK_PAWN_FIVE(BoardSquare.of("d7"), Pawn.getPieceInstance(Color.BLACK)),
    BLACK_PAWN_SIX(BoardSquare.of("c7"), Pawn.getPieceInstance(Color.BLACK)),
    BLACK_PAWN_SEVEN(BoardSquare.of("b7"), Pawn.getPieceInstance(Color.BLACK)),
    BLACK_PAWN_EIGHT(BoardSquare.of("a7"), Pawn.getPieceInstance(Color.BLACK));

    private final BoardSquare boardSquare;
    private final Piece piece;

    ChessInitialSetting(BoardSquare boardSquare, Piece piece) {
        this.boardSquare = boardSquare;
        this.piece = piece;
    }

    public static boolean contains(BoardSquare boardSquare, Piece piece) {
        return Arrays.stream(ChessInitialSetting.values())
            .filter(chessInitialSetting -> chessInitialSetting.boardSquare == boardSquare)
            .anyMatch(chessInitialSetting -> chessInitialSetting.piece == piece);
    }

    public BoardSquare getBoardSquare() {
        return boardSquare;
    }

    public Piece getPiece() {
        return piece;
    }

    public static Set<ChessInitialSetting> getCastlingElements() {
        Set<ChessInitialSetting> castlingElements = new HashSet<>();
        castlingElements.add(BLACK_KING);
        castlingElements.add(WHITE_KING);
        castlingElements.add(BLACK_ROOK_LEFT);
        castlingElements.add(BLACK_ROOK_RIGHT);
        castlingElements.add(WHITE_ROOK_LEFT);
        castlingElements.add(WHITE_ROOK_RIGHT);
        return castlingElements;
    }
}
