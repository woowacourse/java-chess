package chess.utils;

import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class MoveInfo {
    private static final String DELIMITER = " ";
    private static final int FROM = 1;
    private static final int TO = 2;

    private final String from;
    private final String to;

    private MoveInfo(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public static MoveInfo of(String moveInfo) {
        List<String> infos = Arrays.asList(moveInfo.split(DELIMITER));
        validate(infos);
        return new MoveInfo(infos.get(FROM), infos.get(TO));
    }

    private static void validate(List<String> infos) {
        if (Position.of(infos.get(FROM)) == null || Position.of(infos.get(TO)) == null) {
            throw new IllegalArgumentException("위치값을 잘못 입력하셨습니다.");
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
