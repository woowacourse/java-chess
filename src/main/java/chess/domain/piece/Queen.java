package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;
import java.util.stream.IntStream;

public class Queen extends JumpPiece {

    public static final int BOARD_ONE_LINE_SIZE = 8;

    public Queen(Team team, Position position) {
        super(team, position, 9);
    }

    @Override
    protected Direction findDirection(Position destination) {
        int colDiff = destination.getCol().getDifference(position.getCol());
        int rowDiff = destination.getRow().getDifference(position.getRow());

        return Direction.everyDirection().stream()
                .filter(direction -> isMatch(colDiff, rowDiff, direction))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다."));
    }

    private boolean isMatch(int colDiff, int rowDiff, Direction direction) {
        return IntStream.rangeClosed(1, BOARD_ONE_LINE_SIZE)
                .filter(i -> rowDiff == direction.getYDegree() * i && colDiff == direction.getXDegree() * i)
                .findAny()
                .isPresent();
    }
}
