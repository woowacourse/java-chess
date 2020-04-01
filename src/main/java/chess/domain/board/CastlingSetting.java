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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    private static final List<String> KEYS;
    private static final Set<Map<String, CastlingSetting>> TOTALS;

    private static final int KEYS_KING_BEFORE = 0;

    private static final int KEYS_KING_AFTER = 1;

    private static final int KEYS_ROOK_BEFORE = 2;

    private static final int KEYS_ROOK_AFTER = 3;

    static {
        List<String> keys = new ArrayList<>();
        keys.add("KingBefore");
        keys.add("KingAfter");
        keys.add("RookBefore");
        keys.add("RookAfter");
        KEYS = Collections.unmodifiableList(new ArrayList<>(keys));

        Map<String, CastlingSetting> blackLeft = new HashMap<>();
        blackLeft.put(KEYS.get(KEYS_KING_BEFORE), BLACK_KING_BEFORE);
        blackLeft.put(KEYS.get(KEYS_KING_AFTER), BLACK_KING_LEFT_AFTER);
        blackLeft.put(KEYS.get(KEYS_ROOK_BEFORE), BLACK_ROOK_LEFT_BEFORE);
        blackLeft.put(KEYS.get(KEYS_ROOK_AFTER), BLACK_ROOK_LEFT_AFTER);

        Map<String, CastlingSetting> blackRight = new HashMap<>();
        blackRight.put(KEYS.get(KEYS_KING_BEFORE), BLACK_KING_BEFORE);
        blackRight.put(KEYS.get(KEYS_KING_AFTER), BLACK_KING_RIGHT_AFTER);
        blackRight.put(KEYS.get(KEYS_ROOK_BEFORE), BLACK_ROOK_RIGHT_BEFORE);
        blackRight.put(KEYS.get(KEYS_ROOK_AFTER), BLACK_ROOK_RIGHT_AFTER);

        Map<String, CastlingSetting> whiteLeft = new HashMap<>();
        whiteLeft.put(KEYS.get(KEYS_KING_BEFORE), WHITE_KING_BEFORE);
        whiteLeft.put(KEYS.get(KEYS_KING_AFTER), WHITE_KING_LEFT_AFTER);
        whiteLeft.put(KEYS.get(KEYS_ROOK_BEFORE), WHITE_ROOK_LEFT_BEFORE);
        whiteLeft.put(KEYS.get(KEYS_ROOK_AFTER), WHITE_ROOK_LEFT_AFTER);

        Map<String, CastlingSetting> whiteRight = new HashMap<>();
        whiteRight.put(KEYS.get(KEYS_KING_BEFORE), WHITE_KING_BEFORE);
        whiteRight.put(KEYS.get(KEYS_KING_AFTER), WHITE_KING_RIGHT_AFTER);
        whiteRight.put(KEYS.get(KEYS_ROOK_BEFORE), WHITE_ROOK_RIGHT_BEFORE);
        whiteRight.put(KEYS.get(KEYS_ROOK_AFTER), WHITE_ROOK_RIGHT_AFTER);

        Set<Map<String, CastlingSetting>> totals = new HashSet<>();
        totals.add(blackLeft);
        totals.add(blackRight);
        totals.add(whiteLeft);
        totals.add(whiteRight);
        TOTALS = Collections.unmodifiableSet(new HashSet<>(totals));
    }

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
        Map<String, CastlingSetting> selectCastling = TOTALS.stream()
            .filter(total -> moveSquareAfter == total.get(KEYS.get(KEYS_KING_AFTER)).boardSquare)
            .findFirst()
            .orElseThrow(IllegalAccessError::new);
        return new MoveSquare(selectCastling.get(KEYS.get(KEYS_ROOK_BEFORE)).boardSquare,
            selectCastling.get(KEYS.get(KEYS_ROOK_AFTER)).boardSquare);
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
