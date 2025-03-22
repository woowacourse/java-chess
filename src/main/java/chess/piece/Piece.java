package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;

public abstract class Piece {
    private final String name;
    private final Color color;
    private final Position position;
    private final Moving moving;

    public Piece(String name, Color color, Position position, Moving moving) {
        this.name = name;
        this.color = color;
        this.position = position;
        this.moving = moving;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public abstract List<List<Movement>> getMovement();

    public Color getColor() {
        return color;
    }

    public abstract Piece move(Map<Position, Piece> board, Position positionToMove);

    protected void validate(Map<Position, Piece> board, Position positionToMove) {
        if (!checkCanMove(board, positionToMove)) {
            throw new IllegalArgumentException(getPosition() + " " + positionToMove + "는 움직일 수 없습니다");
        }
        if (board.get(positionToMove).getColor().equals(getColor())) {
            throw new IllegalArgumentException("같은 팀은 잡을 수 없습니다");
        }
    }


    private boolean checkCanMove(Map<Position, Piece> board, Position positionToMove) {
        return moving.checkCanMove(board, position, positionToMove, getMovement());
    }
}
