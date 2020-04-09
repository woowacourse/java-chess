package chess.domain.game;

import chess.domain.exception.WrongOperationException;
import chess.domain.piece.Color;

import java.util.Arrays;
import java.util.List;

public class Turn {
    private static final List<Turn> turns;

    private final Color color;

    static {
        turns = Arrays.asList(
                new Turn(Color.WHITE),
                new Turn(Color.BLACK));
    }

    public Turn(Color color) {
        validate(color);
        this.color = color;
    }

    public Turn(String name) {
        color = findColorByName(name);
    }

    private Color findColorByName(String name) {
        return turns.stream()
                .map(Turn::getColor)
                .filter(turnColor -> turnColor.toString().equals(name))
                .findFirst()
                .orElseThrow(WrongOperationException::new);
    }

    private void validate(Color color) {
        if (color.isNone()) {
            throw new IllegalArgumentException("턴은 BLACK이나 WHITE로만 시작할 수 있습니다.");
        }
    }

    public Turn change() {
        if (color.isWhite()) {
            return turns.stream()
                    .filter(turn -> turn.getColor().isBlack())
                    .findFirst()
                    .orElseThrow(UnsupportedOperationException::new);
        }
        return turns.stream()
                .filter(turn -> turn.getColor().isWhite())
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new);
    }

    public Color getColor() {
        return color;
    }
}
