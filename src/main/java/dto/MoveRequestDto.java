package dto;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import domain.game.GameCommand;

public record MoveRequestDto(GameCommand gameCommand, PositionDto source, PositionDto destination) {

    public static MoveRequestDto of(final GameCommand gameCommand, final PositionDto source, final PositionDto destination) {
        return new MoveRequestDto(gameCommand, source, destination);
    }

    public static MoveRequestDto of(final GameCommand gameCommand) {
        return new MoveRequestDto(gameCommand, PositionDto.emptyPosition(), PositionDto.emptyPosition());
    }

    public boolean isEndCommand() {
        return !gameCommand.isMoveCommand();
    }

    public Position sourcePosition() {
        File file = File.of(source.row());
        Rank rank = Rank.of(source.column());

        return new Position(file, rank);
    }

    public Position destinationPosition() {
        File file = File.of(destination.row());
        Rank rank = Rank.of(destination.column());

        return new Position(file, rank);
    }
}
