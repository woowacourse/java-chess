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
        File sourceFile = FileResolver.resolveFile(Character.toString(sourcePositionText.charAt(0)));
        Rank sourceRank = RankResolver.resolveRank(Character.toString(sourcePositionText.charAt(1)));
        Position sourcePosition = new Position(sourceFile, sourceRank);

        File targetFile = FileResolver.resolveFile(Character.toString(targetPositionText.charAt(0)));
        Rank targetRank = RankResolver.resolveRank(Character.toString(targetPositionText.charAt(1)));
        Position targetPosition = new Position(targetFile, targetRank);

        return new MovePositionDto(sourcePosition, targetPosition);
    }
}
