package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.state.MoveSquare;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum ChessInitialSetting {
    WHITE_ROOK_LEFT(BoardSquare.of("a1"), Rook.getPieceInstance(Color.WHITE), true),
    WHITE_ROOK_RIGHT(BoardSquare.of("h1"), Rook.getPieceInstance(Color.WHITE), true),
    BLACK_ROOK_LEFT(BoardSquare.of("h8"), Rook.getPieceInstance(Color.BLACK), true),
    BLACK_ROOK_RIGHT(BoardSquare.of("a8"), Rook.getPieceInstance(Color.BLACK), true),
    WHITE_KNIGHT_LEFT(BoardSquare.of("b1"), Knight.getPieceInstance(Color.WHITE), false),
    WHITE_KNIGHT_RIGHT(BoardSquare.of("g1"), Knight.getPieceInstance(Color.WHITE), false),
    BLACK_KNIGHT_LEFT(BoardSquare.of("g8"), Knight.getPieceInstance(Color.BLACK), false),
    BLACK_KNIGHT_RIGHT(BoardSquare.of("b8"), Knight.getPieceInstance(Color.BLACK), false),
    WHITE_BISHOP_LEFT(BoardSquare.of("c1"), Bishop.getPieceInstance(Color.WHITE), false),
    WHITE_BISHOP_RIGHT(BoardSquare.of("f1"), Bishop.getPieceInstance(Color.WHITE), false),
    BLACK_BISHOP_LEFT(BoardSquare.of("f8"), Bishop.getPieceInstance(Color.BLACK), false),
    BLACK_BISHOP_RIGHT(BoardSquare.of("c8"), Bishop.getPieceInstance(Color.BLACK), false),
    WHITE_QUEEN(BoardSquare.of("d1"), Queen.getPieceInstance(Color.WHITE), false),
    BLACK_QUEEN(BoardSquare.of("d8"), Queen.getPieceInstance(Color.BLACK), false),
    WHITE_KING(BoardSquare.of("e1"), King.getPieceInstance(Color.WHITE), true),
    BLACK_KING(BoardSquare.of("e8"), King.getPieceInstance(Color.BLACK), true),
    WHITE_PAWN_ONE(BoardSquare.of("a2"), Pawn.getPieceInstance(Color.WHITE), false),
    WHITE_PAWN_TWO(BoardSquare.of("b2"), Pawn.getPieceInstance(Color.WHITE), false),
    WHITE_PAWN_THREE(BoardSquare.of("c2"), Pawn.getPieceInstance(Color.WHITE), false),
    WHITE_PAWN_FOUR(BoardSquare.of("d2"), Pawn.getPieceInstance(Color.WHITE), false),
    WHITE_PAWN_FIVE(BoardSquare.of("e2"), Pawn.getPieceInstance(Color.WHITE), false),
    WHITE_PAWN_SIX(BoardSquare.of("f2"), Pawn.getPieceInstance(Color.WHITE), false),
    WHITE_PAWN_SEVEN(BoardSquare.of("g2"), Pawn.getPieceInstance(Color.WHITE), false),
    WHITE_PAWN_EIGHT(BoardSquare.of("h2"), Pawn.getPieceInstance(Color.WHITE), false),
    BLACK_PAWN_ONE(BoardSquare.of("h7"), Pawn.getPieceInstance(Color.BLACK), false),
    BLACK_PAWN_TWO(BoardSquare.of("g7"), Pawn.getPieceInstance(Color.BLACK), false),
    BLACK_PAWN_THREE(BoardSquare.of("f7"), Pawn.getPieceInstance(Color.BLACK), false),
    BLACK_PAWN_FOUR(BoardSquare.of("e7"), Pawn.getPieceInstance(Color.BLACK), false),
    BLACK_PAWN_FIVE(BoardSquare.of("d7"), Pawn.getPieceInstance(Color.BLACK), false),
    BLACK_PAWN_SIX(BoardSquare.of("c7"), Pawn.getPieceInstance(Color.BLACK), false),
    BLACK_PAWN_SEVEN(BoardSquare.of("b7"), Pawn.getPieceInstance(Color.BLACK), false),
    BLACK_PAWN_EIGHT(BoardSquare.of("a7"), Pawn.getPieceInstance(Color.BLACK), false);

    private final BoardSquare boardSquare;
    private final Piece piece;
    private final boolean CastlingPiece;

    ChessInitialSetting(BoardSquare boardSquare, Piece piece, boolean castlingPiece) {
        this.boardSquare = boardSquare;
        this.piece = piece;
        CastlingPiece = castlingPiece;
    }

    public static boolean isContainsSquare(BoardSquare boardSquare, Piece piece) {
        return Arrays.stream(ChessInitialSetting.values())
            .filter(chessInitialSetting -> chessInitialSetting.boardSquare == boardSquare)
            .anyMatch(chessInitialSetting -> chessInitialSetting.piece == piece);
    }

    public static MoveSquare getMoveCastlingRook(BoardSquare moveSquareAfter) {
        if (moveSquareAfter == WHITE_BISHOP_LEFT.boardSquare) {
            return new MoveSquare(WHITE_ROOK_LEFT.boardSquare, WHITE_QUEEN.boardSquare);
        }
        if (moveSquareAfter == WHITE_KNIGHT_RIGHT.boardSquare) {
            return new MoveSquare(WHITE_ROOK_RIGHT.boardSquare, WHITE_BISHOP_RIGHT.boardSquare);
        }
        if (moveSquareAfter == BLACK_KNIGHT_LEFT.boardSquare) {
            return new MoveSquare(BLACK_ROOK_LEFT.boardSquare, WHITE_BISHOP_LEFT.boardSquare);
        }
        if (moveSquareAfter == BLACK_BISHOP_RIGHT.boardSquare) {
            return new MoveSquare(BLACK_ROOK_RIGHT.boardSquare, BLACK_QUEEN.boardSquare);
        }
        throw new IllegalArgumentException("잘못된 인자");
    }

    public BoardSquare getBoardSquare() {
        return boardSquare;
    }

    public Piece getPiece() {
        return piece;
    }

    public static Set<ChessInitialSetting> getCastlingElements() {
        return Arrays.stream(ChessInitialSetting.values())
            .filter(castlingElement -> castlingElement.CastlingPiece)
            .collect(Collectors.toSet());
    }

    public boolean isContainsSquare(BoardSquare boardSquare) {
        return this.boardSquare == boardSquare;
    }

    public boolean isSameColor(Piece piece) {
        return this.piece.isSameColor(piece);
    }

    public static Set<BoardSquare> getCastlingCheatSheets(
        Set<ChessInitialSetting> castlingElements) {
        Set<BoardSquare> castlingCheatSheets = new HashSet<>();
        if (castlingElements.contains(WHITE_ROOK_LEFT) && castlingElements.contains(WHITE_KING)) {
            castlingCheatSheets.add(WHITE_BISHOP_LEFT.boardSquare);
        }
        if (castlingElements.contains(WHITE_ROOK_RIGHT) && castlingElements.contains(WHITE_KING)) {
            castlingCheatSheets.add(WHITE_KNIGHT_RIGHT.boardSquare);
        }
        if (castlingElements.contains(BLACK_ROOK_LEFT) && castlingElements.contains(BLACK_KING)) {
            castlingCheatSheets.add(BLACK_KNIGHT_LEFT.boardSquare);
        }
        if (castlingElements.contains(BLACK_ROOK_RIGHT) && castlingElements.contains(BLACK_KING)) {
            castlingCheatSheets.add(BLACK_BISHOP_RIGHT.boardSquare);
        }
        return Collections.unmodifiableSet(castlingCheatSheets);
    }

    public boolean isSameSquare(BoardSquare moveSquare) {
        return this.boardSquare == moveSquare;
    }
}
