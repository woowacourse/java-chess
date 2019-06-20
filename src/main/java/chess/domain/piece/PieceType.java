package chess.domain.piece;

import chess.domain.board.PlayerType;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public enum PieceType {
    PAWN("p", (playerType) -> Optional.of(new Pawn(playerType))),
    ROOK("r", (playerType) -> Optional.of(new Rook(playerType))),
    KNIGHT("n", (playerType) -> Optional.of(new Knight(playerType))),
    KING("k", (playerType) -> Optional.of(new King(playerType))),
    BISHOP("b", (playerType) -> Optional.of(new Bishop(playerType))),
    QUEEN("q", (playerType) -> Optional.of(new Queen(playerType))),
    EMPTY(".", (playerType -> Optional.empty()));


    private String piece;
    private Function<PlayerType,Optional<Piece>> create;

    PieceType(String piece, Function<PlayerType,Optional<Piece>> create) {
        this.piece = piece;
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
