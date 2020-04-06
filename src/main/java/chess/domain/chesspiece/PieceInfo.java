package chess.domain.chesspiece;

import chess.domain.game.Player;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.Objects;
import java.util.function.Function;

public enum PieceInfo {
    KING("K", 0, 1, 1, King::new),
    QUEEN("Q", 9, Row.values().length, Column.values().length, Queen::new),
    BISHOP("B", 3, Row.values().length, Column.values().length, Bishop::new),
    ROOK("R", 5, Row.values().length, Column.values().length, Rook::new),
    KNIGHT("N", 2.5, 2, 2, King::new),
    PAWN("P", 1, 1, 1, Pawn::new);

    public static final double PAWN_SCORE_DIFF = 0.5;
    public static final int PAWN_INIT_MOVABLE_COLUMN_DIFF = 2;

    private final String name;
    private final double score;
    private final int movableRowDiff;
    private final int movableColumnDiff;
    private final Function<Player, Piece> findPiece;

    PieceInfo(String name, double score, int movableRowDiff, int movableColumnDiff, Function<Player, Piece> findPiece) {
        this.name = name;
        this.score = score;
        this.movableRowDiff = movableRowDiff;
        this.movableColumnDiff = movableColumnDiff;
        this.findPiece = findPiece;
    }

    public final String getName(Player player) {
        Objects.requireNonNull(player);
        if (player == Player.WHITE) {
            return name.toLowerCase();
        }
        if (player == Player.BLACK) {
            return name.toUpperCase();
        }
        throw new IllegalArgumentException("없는 사용자 입니다.");
    }

    public double getScore() {
        return score;
    }

    public int getMovableRowDiff() {
        return movableRowDiff;
    }

    public int getMovableColumnDiff() {
        return movableColumnDiff;
    }

    public Piece of(Player player) {
        return findPiece.apply(player);
    }
}