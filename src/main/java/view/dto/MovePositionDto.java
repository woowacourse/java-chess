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
        String[] splitCommand = command.split(" ");
        if (splitCommand.length != 3) {
            throw new IllegalArgumentException("형식에 맞는 입력을 해주세요 ex) move b2 b3");
        }
        String sourceText = splitCommand[1];
        String targetText = splitCommand[2];
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
