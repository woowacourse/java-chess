package chess.model.game;

import chess.model.piece.Piece;
import chess.model.piece.Side;

import java.util.Arrays;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Turn {
    private static final Map<Side, Turn> CACHE = Arrays.stream(Side.values())
            .collect(toMap(identity(), Turn::new));

    private final Side side;

    private Turn(Side side) {
        this.side = side;
    }

    public static Turn from(Side side) {
        return CACHE.get(side);
    }

    public Turn getNextTurn() {
        return Turn.from(side.getOppositeSide());
    }

    public boolean isNotCorrect(Piece piece) {
        return !piece.isSameSide(side);
    }
}
