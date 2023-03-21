package chess.view;

import chess.domain.board.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PositionMapper {

    public static Position from(String input) {
        List<String> position = splitPosition(input);
        validatePosition(position);
        return new Position(FileCoordinateMapper.findBy(position.get(0)), RankCoordinateMapper.findBy(position.get(1)));
    }

    private static List<String> splitPosition(String input) {
        return Arrays.stream(input.split(""))
                .collect(Collectors.toList());
    }

    private static void validatePosition(List<String> position) {
        if (position.size() != 2) {
            throw new IllegalArgumentException("잘못된 위치를 입력했습니다.");
        }
        FileCoordinateMapper.validate(position.get(0));
        RankCoordinateMapper.validate(position.get(1));
    }

    public static void validate(List<String> input) {
        if (input.size() != 3) {
            return;
        }
        validatePosition(splitPosition(input.get(1)));
        validatePosition(splitPosition(input.get(2)));
    }
}
