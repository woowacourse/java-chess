package chess.controller;

import chess.domain.position.Position;
import chess.view.FileCoordinateView;
import chess.view.RankCoordinateView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum GameCommand {
    START,
    MOVE,
    END,
    EMPTY,
    ;

    public static GameCommand of(String input) {
        if ("start".equalsIgnoreCase(input)) {
            return START;
        }
        if ("move".equalsIgnoreCase(input)) {
            return MOVE;
        }
        if ("end".equalsIgnoreCase(input)) {
            return END;
        }
        throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
    }

    public static void validateCommandSize(int inputSize, int expect) {
        if (inputSize != expect) {
            throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
        }
    }

    public static Position getPosition(String input) {
        List<String> position = Arrays.stream(input.split(""))
                .collect(Collectors.toList());
        return parseToPosition(position);
    }

    private static Position parseToPosition(List<String> position) {
        return new Position(FileCoordinateView.findBy(position.get(0)),
                RankCoordinateView.findBy(position.get(1)));
    }
}
