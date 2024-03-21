package view.dto;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import view.FileResolver;
import view.RankResolver;

public record MovePositionDto(Position sourcePosition, Position targetPosition) {

    public static MovePositionDto from(String command) {
        String sourcePositionText = command.split(" ")[1];
        String targetPositionText = command.split(" ")[2];

        // TODO: PositionResolver에서 한번에 처리
        int sourceFile = Character.getNumericValue(sourcePositionText.charAt(0));
        int sourceRank = Character.getNumericValue(sourcePositionText.charAt(1));
        Position sourcePosition = Position.of(sourceFile, sourceRank);

        int targetFile = Character.getNumericValue(targetPositionText.charAt(0));
        int targetRank = Character.getNumericValue(targetPositionText.charAt(1));
        Position targetPosition = Position.of(targetFile, targetRank);

        return new MovePositionDto(sourcePosition, targetPosition);
    }
}
