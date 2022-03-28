package chess.domain.piece;

import chess.domain.Column;
import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Queen extends JumpPiece {

    public Queen(Team team, Position position) {
        super(team, position, 9);
    }

    @Override
    protected Direction findDirection(Position destination) {
        for (Direction direction : Direction.everyDirection()) {
            for (int i = 1; i <= 8; i++) {
                if (destination.getRow().getDifference(position.getRow()) == direction.getYDegree() * i
                        && destination.getCol().getDifference(position.getCol()) == direction.getXDegree() * i) {
                    return direction;
                }
            }
        }
        throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
    }
}