package chess.view;

import chess.domain.board.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PositionMapper {

    private static final String INVALID_POSITION_MESSAGE = "잘못된 위치를 입력했습니다.";
    private static final int POSITION_SIZE = 2;
    private static final int FILE_COORDINATE_INDEX = 0;
    private static final int RANK_COORDINATE_INDEX = 1;
    private static final int MOVE_INPUT_SIZE = 3;
    private static final int FIRST_POSITION_INDEX = 1;
    private static final int SECOND_POSITION_INDEX = 2;
    private static final String SPLIT_POSITION_DELIMITER = "";

    public static Position from(String input) {
        List<String> position = splitPosition(input);
        validatePosition(position);
        return new Position(FileCoordinateMapper.findBy(position.get(FILE_COORDINATE_INDEX)),
                RankCoordinateMapper.findBy(position.get(RANK_COORDINATE_INDEX)));
    }

    private static List<String> splitPosition(String input) {
        return Arrays.stream(input.split(SPLIT_POSITION_DELIMITER))
                .collect(Collectors.toList());
    }

    private static void validatePosition(List<String> position) {
        if (position.size() != POSITION_SIZE) {
            throw new IllegalArgumentException(INVALID_POSITION_MESSAGE);
        }
    }

    public static void validate(List<String> input) {
        if (input.size() != MOVE_INPUT_SIZE) {
            return;
        }
        validatePosition(splitPosition(input.get(FIRST_POSITION_INDEX)));
        validatePosition(splitPosition(input.get(SECOND_POSITION_INDEX)));
    }
}
