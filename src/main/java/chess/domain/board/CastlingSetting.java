package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.state.MoveOrder;
import chess.domain.state.MoveSquare;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum CastlingSetting {

    WHITE_ROOK_LEFT_BEFORE(BoardSquare.of("a1"), Rook.getPieceInstance(Color.WHITE), true),
    WHITE_KING_BEFORE(BoardSquare.of("e1"), King.getPieceInstance(Color.WHITE), true),
    WHITE_ROOK_RIGHT_BEFORE(BoardSquare.of("h1"), Rook.getPieceInstance(Color.WHITE), true),

    BLACK_ROOK_LEFT_BEFORE(BoardSquare.of("h8"), Rook.getPieceInstance(Color.BLACK), true),
    BLACK_ROOK_RIGHT_BEFORE(BoardSquare.of("a8"), Rook.getPieceInstance(Color.BLACK), true),
    BLACK_KING_BEFORE(BoardSquare.of("e8"), King.getPieceInstance(Color.BLACK), true),

    WHITE_KING_RIGHT_AFTER(BoardSquare.of("g1"), Knight.getPieceInstance(Color.WHITE), false),
    BLACK_KING_LEFT_AFTER(BoardSquare.of("g8"), Knight.getPieceInstance(Color.BLACK), false),
    WHITE_KING_LEFT_AFTER(BoardSquare.of("c1"), Bishop.getPieceInstance(Color.WHITE), false),
    BLACK_KING_RIGHT_AFTER(BoardSquare.of("c8"), Bishop.getPieceInstance(Color.BLACK), false),
    WHITE_ROOK_RIGHT_AFTER(BoardSquare.of("f1"), Bishop.getPieceInstance(Color.WHITE), false),
    BLACK_ROOK_LEFT_AFTER(BoardSquare.of("f8"), Bishop.getPieceInstance(Color.BLACK), false),
    WHITE_ROOK_LEFT_AFTER(BoardSquare.of("d1"), Queen.getPieceInstance(Color.WHITE), false),
    BLACK_ROOK_RIGHT_AFTER(BoardSquare.of("d8"), Queen.getPieceInstance(Color.BLACK), false);

    private final BoardSquare boardSquare;
    private final Piece piece;
    private final boolean CastlingPiece;

    CastlingSetting(BoardSquare boardSquare, Piece piece, boolean castlingPiece) {
        this.boardSquare = boardSquare;
        this.piece = piece;
        CastlingPiece = castlingPiece;

    }

    public static MoveSquare getMoveCastlingRook(MoveSquare moveSquare) {
        BoardSquare moveSquareAfter = moveSquare.get(MoveOrder.AFTER);
        if (moveSquareAfter == WHITE_KING_LEFT_AFTER.boardSquare) {
            return new MoveSquare(WHITE_ROOK_LEFT_BEFORE.boardSquare,
                WHITE_ROOK_LEFT_AFTER.boardSquare);
        }
        if (moveSquareAfter == WHITE_KING_RIGHT_AFTER.boardSquare) {
            return new MoveSquare(WHITE_ROOK_RIGHT_BEFORE.boardSquare,
                WHITE_ROOK_RIGHT_AFTER.boardSquare);
        }
        if (moveSquareAfter == BLACK_KING_LEFT_AFTER.boardSquare) {
            return new MoveSquare(BLACK_ROOK_LEFT_BEFORE.boardSquare,
                BLACK_ROOK_LEFT_AFTER.boardSquare);
        }
        if (moveSquareAfter == BLACK_KING_RIGHT_AFTER.boardSquare) {
            return new MoveSquare(BLACK_ROOK_RIGHT_BEFORE.boardSquare,
                BLACK_ROOK_RIGHT_AFTER.boardSquare);
        }
        throw new IllegalArgumentException("잘못된 인자");
    }

    private static boolean isSameBoardSquare(CastlingSetting before, CastlingSetting after,
        MoveSquare moveSquare) {
        return before.boardSquare == moveSquare.get(MoveOrder.BEFORE)
            && after.boardSquare == moveSquare.get(MoveOrder.AFTER);
    }

    public static boolean canCastling(Set<CastlingSetting> elements, MoveSquare moveSquare) {
        if (elements.contains(WHITE_KING_BEFORE) && elements.contains(WHITE_ROOK_LEFT_BEFORE)
            && isSameBoardSquare(WHITE_KING_BEFORE, WHITE_KING_LEFT_AFTER, moveSquare)) {
            return true;
        }
        if (elements.contains(WHITE_KING_BEFORE) && elements.contains(WHITE_ROOK_RIGHT_BEFORE)
            && isSameBoardSquare(WHITE_KING_BEFORE, WHITE_KING_RIGHT_AFTER, moveSquare)) {
            return true;
        }
        if (elements.contains(BLACK_KING_BEFORE) && elements.contains(BLACK_ROOK_LEFT_BEFORE)
            && isSameBoardSquare(BLACK_KING_BEFORE, BLACK_KING_LEFT_AFTER, moveSquare)) {
            return true;
        }
        return elements.contains(BLACK_KING_BEFORE) && elements.contains(BLACK_ROOK_RIGHT_BEFORE)
            && isSameBoardSquare(BLACK_KING_BEFORE, BLACK_KING_RIGHT_AFTER, moveSquare);
    }

    public Piece getPiece() {
        return piece;
    }

    public static Set<CastlingSetting> getCastlingElements() {
        return Arrays.stream(CastlingSetting.values())
            .filter(castlingElement -> castlingElement.CastlingPiece)
            .collect(Collectors.toSet());
    }

    public boolean isEqualSquare(BoardSquare boardSquare) {
        return this.boardSquare.equals(boardSquare);
    }

    public boolean isSameColor(Piece piece) {
        return this.piece.isSameColor(piece);
    }

    public static Set<BoardSquare> getCastlingCheatSheets(
        Set<CastlingSetting> castlingElements) {
        Set<BoardSquare> castlingCheatSheets = new HashSet<>();
        if (castlingElements.contains(WHITE_ROOK_LEFT_BEFORE) && castlingElements.contains(
            WHITE_KING_BEFORE)) {
            castlingCheatSheets.add(WHITE_KING_LEFT_AFTER.boardSquare);
        }
        if (castlingElements.contains(WHITE_ROOK_RIGHT_BEFORE) && castlingElements.contains(
            WHITE_KING_BEFORE)) {
            castlingCheatSheets.add(WHITE_KING_RIGHT_AFTER.boardSquare);
        }
        if (castlingElements.contains(BLACK_ROOK_LEFT_BEFORE) && castlingElements
            .contains(BLACK_KING_BEFORE)) {
            castlingCheatSheets.add(BLACK_KING_LEFT_AFTER.boardSquare);
        }
        if (castlingElements.contains(BLACK_ROOK_RIGHT_BEFORE) && castlingElements
            .contains(BLACK_KING_BEFORE)) {
            castlingCheatSheets.add(BLACK_KING_RIGHT_AFTER.boardSquare);
        }
        return Collections.unmodifiableSet(castlingCheatSheets);
    }

    public boolean isContains(BoardSquare moveSquare) {
        return this.boardSquare == moveSquare;
    }

    @Override
    public String toString() {
        return boardSquare + "-" + piece.getLetter();
    }
}
