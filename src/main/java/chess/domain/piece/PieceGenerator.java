package chess.domain.piece;

import chess.domain.Position;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * class description
 *
 * @author hotheadfactory
 */
public enum PieceGenerator {
    BISHOP("B", (position, team) -> new Bishop(new Position(position), Team.valueOf(team))),
    KING("K", (position, team) -> new King(new Position(position), Team.valueOf(team))),
    KNIGHT("N", (position, team) -> new Knight(new Position(position), Team.valueOf(team))),
    PAWN("P", (position, team) -> new Pawn(new Position(position), Team.valueOf(team))),
    QUEEN("Q", (position, team) -> new Queen(new Position(position), Team.valueOf(team))),
    ROOK("R", (position, team) -> new Rook(new Position(position), Team.valueOf(team)));

    private static final String INVALID_TYPE_OF_PIECE = "없는 기물 종류 입니다.";
    private final String representation;
    private final BiFunction<String, String, Piece> function;

    PieceGenerator(String representation, BiFunction<String, String, Piece> function) {
        this.representation = representation;
        this.function = function;
    }

    public static Piece make(String representation, String team, String position) {
        return Arrays.stream(values())
                .filter(p -> p.representation.equals(representation.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_TYPE_OF_PIECE))
                .function
                .apply(position, team);
    }
}
