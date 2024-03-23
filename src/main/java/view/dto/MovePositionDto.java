package view.dto;

import domain.board.Position;
import view.FileResolver;

public class MovePositionDto {

    private final Position source;
    private final Position target;

    private MovePositionDto(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

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

    public Position source() {
        return source;
    }

    public Position target() {
        return target;
    }
}
