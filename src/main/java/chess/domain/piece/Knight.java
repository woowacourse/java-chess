package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public class Knight extends Piece {

    public Knight(Team team, Position position) {
        super(team, position, 2.5);
    }

    public void validateIsPossible(Position destination) {
        int colDiff = destination.getCol().getDifference(position.getCol());
        int rowDiff = destination.getRow().getDifference(position.getRow());

        Direction.knightDirection().stream()
                .filter(direction -> isMatch(colDiff, rowDiff, direction))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다."));
    }

    private boolean isMatch(int colDiff, int rowDiff, Direction direction) {
        return rowDiff == direction.getYDegree() && colDiff == direction.getXDegree();
    }

    @Override
    public List<Position> findPath(Position destination) {
        validateIsPossible(destination);
        return List.of();
    }

    @Override
    public boolean isBlank() {
        return false;
    }
}