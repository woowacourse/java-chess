package chess.repository.chess;

import chess.domain.game.Position;
import java.util.Objects;

public class MoveDto {

    private final int gameId;
    private final int turn;
    private final Position origin;
    private final Position destination;

    private MoveDto(int gameId, int turn, Position origin, Position destination) {
        this.gameId = gameId;
        this.origin = origin;
        this.destination = destination;
        this.turn = turn;
    }

    public static MoveDto of(int gameId, String origin, String destination, int turn) {
        return new MoveDto(gameId, turn, Position.from(origin), Position.from(destination));
    }

    public Position getOrigin() {
        return origin;
    }

    public Position getDestination() {
        return destination;
    }

    public int getTurn() {
        return turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MoveDto moveDto = (MoveDto) o;
        return gameId == moveDto.gameId && turn == moveDto.turn && Objects.equals(origin, moveDto.origin)
                && Objects.equals(destination, moveDto.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, turn, origin, destination);
    }
}
