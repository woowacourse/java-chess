package view.dto;

import domain.board.Position;
import view.FileResolver;

public record MovePositionDto(Position source, Position target) {

    public static MovePositionDto from(String command) {
        String sourceText = command.split(" ")[1];
        String targetText = command.split(" ")[2];
        return new MovePositionDto(resolvePosition(sourceText), resolvePosition(targetText));
    }

    private static Position resolvePosition(String positionText) {
        int sourceFile = FileResolver.resolveFile(positionText.charAt(0));
        int sourceRank = Character.getNumericValue(positionText.charAt(1));
        return Position.of(sourceFile, sourceRank);
    }
}
