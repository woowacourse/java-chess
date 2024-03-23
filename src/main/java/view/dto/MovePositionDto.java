package view.dto;

import domain.board.Position;
import domain.game.Turn;
import view.FileResolver;

public record MovePositionDto(Position sourcePosition, Position targetPosition, Turn turn) {

    public static MovePositionDto from(String command, Turn turn) {
        String sourcePositionText = command.split(" ")[1];
        String targetPositionText = command.split(" ")[2];
        return new MovePositionDto(resolvePosition(sourcePositionText), resolvePosition(targetPositionText), turn);
    }

    private static Position resolvePosition(String positionText) {
        int sourceFile = FileResolver.resolveFile(positionText.charAt(0));
        int sourceRank = Character.getNumericValue(positionText.charAt(1));
        return Position.of(sourceFile, sourceRank);
    }
}
