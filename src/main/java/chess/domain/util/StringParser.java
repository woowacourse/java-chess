package chess.domain.util;

import chess.domain.position.File;
import chess.domain.position.MovePath;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class StringParser {
    public static final int COMMAND_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public static MovePath splitSourceAndTargetPosition(String command) {
        String[] commandParameters = command.split(" ");
        if (commandParameters.length != COMMAND_SIZE) {
            throw new IllegalArgumentException("[ERROR] move 명령어는 두 개의 좌표가 필요합니다.");
        }
        String source = commandParameters[SOURCE_INDEX];
        String target = commandParameters[TARGET_INDEX];

        return new MovePath(
            Position.of(convertFileToCoordinate(source), convertRankToCoordinate(source)),
            Position.of(convertFileToCoordinate(target), convertRankToCoordinate(target)));
    }

    private static Rank convertRankToCoordinate(String coordinate) {
        return Rank.of(Integer.parseInt(String.valueOf(coordinate.charAt(1))));
    }

    private static File convertFileToCoordinate(String coordinate) {
        return File.of(String.valueOf(coordinate.charAt(0)));
    }
}
