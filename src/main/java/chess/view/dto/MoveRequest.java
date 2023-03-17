package chess.view.dto;

import chess.view.InputView;
import java.util.Arrays;

public class MoveRequest {

    private final PositionRequest source;
    private final PositionRequest target;

    private MoveRequest(PositionRequest source, PositionRequest target) {
        this.source = source;
        this.target = target;
    }

    public static MoveRequest of(String source, String target) {
        return new MoveRequest(createPosition(source), createPosition(target));

    }

    private static PositionRequest createPosition(String input) {
        return new PositionRequest(createFile(input), createRank(input));
    }

    private static String createFile(String input) {
        return Arrays.stream(File.values())
                .map(Enum::name)
                .filter(name -> name.equals(Character.toString(input.charAt(0))))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(InputView.INVALID_INPUT_MESSAGE));
    }

    private static String createRank(String input) {
        return Arrays.stream(Rank.values())
                .filter(value -> value.ordinal() + 1 == input.charAt(1) - '0')
                .map(Enum::name)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(InputView.INVALID_INPUT_MESSAGE));
    }

    private enum File {
        A, B, C, D, E, F, G, H
    }

    private enum Rank {
        ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT
    }

    public PositionRequest getSource() {
        return source;
    }

    public PositionRequest getTarget() {
        return target;
    }
}
