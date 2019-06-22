package chess.domain.piece;

import chess.domain.board.PlayerType;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public enum PieceType {
    PAWN("p", 1, (playerType) -> Optional.of(new Pawn(playerType))),
    ROOK("r", 5, (playerType) -> Optional.of(new Rook(playerType))),
    KNIGHT("n", 2.5, (playerType) -> Optional.of(new Knight(playerType))),
    KING("k", 0, (playerType) -> Optional.of(new King(playerType))),
    BISHOP("b", 3, (playerType) -> Optional.of(new Bishop(playerType))),
    QUEEN("q", 9, (playerType) -> Optional.of(new Queen(playerType))),
    EMPTY(".", 0, (playerType -> Optional.empty()));


    private String piece;
    private double score;
    private Function<PlayerType, Optional<Piece>> create;

    PieceType(String piece, double score, Function<PlayerType, Optional<Piece>> create) {
        this.piece = piece;
        this.score = score;
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
}
