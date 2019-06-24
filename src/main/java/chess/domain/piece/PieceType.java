package chess.domain.piece;

import chess.domain.board.PlayerType;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public enum PieceType {
    PAWN("p", count -> (count > 1) ? count * 0.5 : count, playerType -> Optional.of(new Pawn(playerType))),
    ROOK("r", count -> (double) count * 5, playerType -> Optional.of(new Rook(playerType))),
    KNIGHT("n", count -> count * 2.5, playerType -> Optional.of(new Knight(playerType))),
    KING("k", count -> (double) 0, playerType -> Optional.of(new King(playerType))),
    BISHOP("b", count -> (double) count * 3, playerType -> Optional.of(new Bishop(playerType))),
    QUEEN("q", count -> (double) count * 9, playerType -> Optional.of(new Queen(playerType))),
    EMPTY(".", count -> (double) 0, playerType -> Optional.empty());


    private String piece;
    private Function<Long, Double> calculateScore;
    private Function<PlayerType, Optional<Piece>> create;

    PieceType(String piece, Function<Long, Double> calculateScore, Function<PlayerType, Optional<Piece>> create) {
        this.piece = piece;
        this.calculateScore = calculateScore;
        this.create = create;
    }

    public static PieceType of(String piece) {
        return Arrays.stream(values())
                // TODO method 분리
                .filter(x -> x.piece.equals(piece.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    public Optional<Piece> create(PlayerType playerType) {
        return this.create.apply(playerType);
    }

    public boolean isKing() {
        return this == KING;
    }

    public boolean isPawn() {
        return this == PAWN;
    }

    public boolean isKnight() {
        return this == KNIGHT;
    }

    public double calculateScore(long count) {
        return calculateScore.apply(count);
    }
}
