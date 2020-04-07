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
import java.util.HashMap;
import java.util.HashSet;
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

    private static final Set<Map<String, CastlingSetting>> TOTALS;
    private static final String KEYS_KING_BEFORE = "KING_BEFORE";
    private static final String KEYS_KING_AFTER = "KING_AFTER";
    private static final String KEYS_ROOK_BEFORE = "ROOK_BEFORE";
    private static final String KEYS_ROOK_AFTER = "ROOK_AFTER";

    static {
        Map<String, CastlingSetting> blackLeft = new HashMap<>();
        blackLeft.put(KEYS_KING_BEFORE, BLACK_KING_BEFORE);
        blackLeft.put(KEYS_KING_AFTER, BLACK_KING_LEFT_AFTER);
        blackLeft.put(KEYS_ROOK_BEFORE, BLACK_ROOK_LEFT_BEFORE);
        blackLeft.put(KEYS_ROOK_AFTER, BLACK_ROOK_LEFT_AFTER);

        Map<String, CastlingSetting> blackRight = new HashMap<>();
        blackRight.put(KEYS_KING_BEFORE, BLACK_KING_BEFORE);
        blackRight.put(KEYS_KING_AFTER, BLACK_KING_RIGHT_AFTER);
        blackRight.put(KEYS_ROOK_BEFORE, BLACK_ROOK_RIGHT_BEFORE);
        blackRight.put(KEYS_ROOK_AFTER, BLACK_ROOK_RIGHT_AFTER);

        Map<String, CastlingSetting> whiteLeft = new HashMap<>();
        whiteLeft.put(KEYS_KING_BEFORE, WHITE_KING_BEFORE);
        whiteLeft.put(KEYS_KING_AFTER, WHITE_KING_LEFT_AFTER);
        whiteLeft.put(KEYS_ROOK_BEFORE, WHITE_ROOK_LEFT_BEFORE);
        whiteLeft.put(KEYS_ROOK_AFTER, WHITE_ROOK_LEFT_AFTER);

        Map<String, CastlingSetting> whiteRight = new HashMap<>();
        whiteRight.put(KEYS_KING_BEFORE, WHITE_KING_BEFORE);
        whiteRight.put(KEYS_KING_AFTER, WHITE_KING_RIGHT_AFTER);
        whiteRight.put(KEYS_ROOK_BEFORE, WHITE_ROOK_RIGHT_BEFORE);
        whiteRight.put(KEYS_ROOK_AFTER, WHITE_ROOK_RIGHT_AFTER);

        Set<Map<String, CastlingSetting>> totals = new HashSet<>();
        totals.add(blackLeft);
        totals.add(blackRight);
        totals.add(whiteLeft);
        totals.add(whiteRight);
        TOTALS = Collections.unmodifiableSet(new HashSet<>(totals));
    }

    private final BoardSquare boardSquare;
    private final Piece piece;
    private final boolean castlingPiece;

    CastlingSetting(BoardSquare boardSquare, Piece piece, boolean castlingPiece) {
        this.boardSquare = boardSquare;
        this.piece = piece;
        this.castlingPiece = castlingPiece;
    }

    public static MoveSquare getMoveCastlingRook(MoveSquare moveSquare) {
        BoardSquare moveSquareAfter = moveSquare.get(MoveOrder.AFTER);
        Map<String, CastlingSetting> selectCastling = TOTALS.stream()
            .filter(total -> moveSquareAfter == total.get(KEYS_KING_AFTER).boardSquare)
            .findFirst()
            .orElseThrow(IllegalAccessError::new);
        return new MoveSquare(selectCastling.get(KEYS_ROOK_BEFORE).boardSquare,
            selectCastling.get(KEYS_ROOK_AFTER).boardSquare);
    }

    public static boolean canCastling(Set<CastlingSetting> elements, MoveSquare moveSquare) {
        return TOTALS.stream()
            .filter(total -> elements.contains(total.get(KEYS_KING_BEFORE)))
            .filter(total -> elements.contains(total.get(KEYS_ROOK_BEFORE)))
            .filter(total -> total.get(KEYS_KING_BEFORE).boardSquare
                == moveSquare.get(MoveOrder.BEFORE))
            .anyMatch(total -> total.get(KEYS_KING_AFTER).boardSquare
                == moveSquare.get(MoveOrder.AFTER));
    }

    public static CastlingSetting of(String castlingSettingName) {
        return Arrays.stream(CastlingSetting.values())
            .filter(castlingSetting -> castlingSetting.name().equalsIgnoreCase(castlingSettingName))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public static CastlingSetting of(BoardSquare boardsquare, Piece piece) {
        return Arrays.stream(CastlingSetting.values())
            .filter(castlingSetting -> castlingSetting.boardSquare == boardsquare)
            .filter(castlingSetting -> castlingSetting.piece == piece)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public Piece getPiece() {
        return piece;
    }

    public static Set<CastlingSetting> getCastlingElements() {
        return Arrays.stream(CastlingSetting.values())
            .filter(castlingElement -> castlingElement.castlingPiece)
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
        return Collections.unmodifiableSet(TOTALS.stream()
            .filter(total -> castlingElements.contains(total.get(KEYS_ROOK_BEFORE)))
            .filter(total -> castlingElements.contains(total.get(KEYS_KING_BEFORE)))
            .map(total -> total.get(KEYS_KING_AFTER).boardSquare)
            .collect(Collectors.toSet()));
    }

    public boolean isContains(BoardSquare moveSquare) {
        return this.boardSquare == moveSquare;
    }

    @Override
    public String toString() {
        return boardSquare + "-" + piece.getLetter();
    }

    public boolean isCastlingBefore(BoardSquare boardSquare, Piece piece) {
        return Arrays.stream(CastlingSetting.values())
            .filter(castlingSetting -> castlingSetting.boardSquare == boardSquare)
            .filter(castlingSetting -> castlingSetting.piece == piece)
            .findFirst()
            .map(castlingSetting -> castlingSetting.castlingPiece)
            .orElse(false);
    }
}
