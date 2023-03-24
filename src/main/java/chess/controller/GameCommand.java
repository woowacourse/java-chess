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
    STATUS,
    ;

    private static final int SOURCE_POSITION = 0;
    private static final int TARGET_POSITION = 1;

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
        if ("status".equalsIgnoreCase(input)) {
            return STATUS;
        }
        throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
    }

    public static Position createPosition(String input) {
        List<String> position = Arrays.stream(input.split(""))
                .collect(Collectors.toList());
        return parseToPosition(position);
    }

    private static Position parseToPosition(List<String> position) {
        return new Position(FileCoordinateView.findBy(position.get(SOURCE_POSITION)),
                RankCoordinateView.findBy(position.get(TARGET_POSITION)));
    }
}
