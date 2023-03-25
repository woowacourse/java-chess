package chess.dto;

import chess.domain.position.Position;
import chess.view.FileCoordinateView;
import chess.view.RankCoordinateView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MoveHistory {
    private static final int SOURCE_POSITION = 0;
    private static final int TARGET_POSITION = 1;

    private final String sourcePosition;
    private final String targetPosition;

    public MoveHistory(String sourcePosition, String targetPosition) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public static MoveHistory of(Position sourcePosition, Position targetPosition) {
        String sourceName = parseToString(sourcePosition);
        String targetName = parseToString(targetPosition);
        return new MoveHistory(sourceName, targetName);
    }

    private static String parseToString(Position position) {
        return position.getFileCoordinate().name().toLowerCase() +
                position.getRankCoordinate().getRowNumber();
    }

    public String getSource() {
        return sourcePosition;
    }

    public String getTarget() {
        return targetPosition;
    }

    public Position getSourcePosition() {
        return createPosition(sourcePosition);
    }

    public Position getTargetPosition() {
        return createPosition(targetPosition);
    }

    public Position createPosition(String input) {
        List<String> position = Arrays.stream(input.split(""))
                .collect(Collectors.toList());
        return parseToPosition(position);
    }

    private Position parseToPosition(List<String> position) {
        return new Position(FileCoordinateView.findBy(position.get(SOURCE_POSITION)),
                RankCoordinateView.findBy(position.get(TARGET_POSITION)));
    }
}
