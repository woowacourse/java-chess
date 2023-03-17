package chess.view;

import chess.domain.board.Position;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PositionMapper {

    public static List<Position> from(List<String> input) {
        validateSize(input);
        List<String> pathInput = input.subList(1, 3);
        validatePosition(pathInput);
        return pathInput.stream()
                .map(it -> extracted(getRankAndFile(it)))
                .collect(Collectors.toList());
    }

    private static void validateSize(List<String> input) {
        if (input.size() != 3) {
            throw new IllegalArgumentException("위치를 입력하지 않았습니다");
        }
    }

    private static Position extracted(List<String> position) {
        validatePosition(position);
        return new Position(FileCoordinateView.findBy(position.get(0)),
                RankCoordinateView.findBy(position.get(1)));
    }

    private static List<String> getRankAndFile(String source) {
        List<String> position = Arrays.stream(source.split(""))
                .collect(Collectors.toList());
        return position;
    }

    private static void validatePosition(List<String> position) {
        if (position.size() != 2) {
            throw new IllegalArgumentException("잘못된 위치를 입력했습니다.");
        }
    }
}
