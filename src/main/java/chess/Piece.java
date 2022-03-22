package chess;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Piece {
    BLACK_KING("♚", (file, rank) -> new PiecePosition(file, rank).isBlackKing()),
    BLACK_QUEEN("♛", (file, rank) -> new PiecePosition(file, rank).isBlackQueen()),
    BLACK_ROOK("♜", (file, rank) -> new PiecePosition(file, rank).isBlackRook()),
    BLACK_BISHOP("♝", (file, rank) -> new PiecePosition(file, rank).isBlackBishop()),
    BLACK_KNIGHT("♞", (file, rank) -> new PiecePosition(file, rank).isBlackKnight()),
    BLACK_PAWN("♟", (file, rank) -> new PiecePosition(file, rank).isBlackPawn()),
    WHITE_KING("♔", (file, rank) -> new PiecePosition(file, rank).isWhiteKing()),
    WHITE_QUEEN("♕", (file, rank) -> new PiecePosition(file, rank).isWhiteQueen()),
    WHITE_ROOK("♖", (file, rank) -> new PiecePosition(file, rank).isWhiteRook()),
    WHITE_BISHOP("♗", (file, rank) -> new PiecePosition(file, rank).isWhiteBishop()),
    WHITE_KNIGHT("♘", (file, rank) -> new PiecePosition(file,rank).isWhiteKnight()),
    WHITE_PAWN("♙", (file, rank) -> new PiecePosition(file,rank).isWhitePawn()),
    NONE(".", (file, rank) -> new PiecePosition(file, rank).isNone());

    private final String emoji;
    private final BiPredicate<File, Rank> condition;

    Piece(String emoji, BiPredicate<File, Rank> condition) {
        this.emoji = emoji;
        this.condition = condition;
    }

    public static Piece of(File file,Rank rank) {
        return Arrays.stream(Piece.values())
                .filter(piece -> piece.condition.test(file,rank))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("[ERROR] 체스판 범위를 확인하세요."));
    }
}
