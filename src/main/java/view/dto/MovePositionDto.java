package view.dto;

import domain.board.Position;

public record MovePositionDto(Position sourcePosition, Position targetPosition) {

    public static MovePositionDto from(String command) {
        String sourcePositionText = command.split(" ")[1];
        String targetPositionText = command.split(" ")[2];
        return new MovePositionDto(resolvePosition(sourcePositionText), resolvePosition(targetPositionText));
    }

    private static Position resolvePosition(String positionText) {
        int sourceFile = Character.getNumericValue(positionText.charAt(0));
        int sourceRank = Character.getNumericValue(positionText.charAt(1));
        return Position.of(sourceFile, sourceRank);
    }
}
