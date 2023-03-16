package chess.view;

import chess.domain.board.Position;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PositionMapper {

    public static Position from(String input) {
        List<String> position = Arrays.stream(input.split(""))
                .collect(Collectors.toList());
        validatePosition(position);
        return new Position(FileCoordinateView.findBy(position.get(0)), RankCoordinateView.findBy(position.get(1)));
    }

    private static void validatePosition(List<String> position) {
        if (position.size() != 2) {
            throw new IllegalArgumentException("잘못된 위치를 입력했습니다.");
        }
    }
}
