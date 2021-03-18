package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece {
    private static final String QUEEN_NAME = "Q";
    private final int score = 9;

    public Queen(Team team) {
        super(QUEEN_NAME, team);
    }

    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        Direction direction = target.directionToDestination(destination);
        Position movedPosition = target;
        while (true) {
            movedPosition = movedPosition.moveTowardDirection(direction);

            if (movedPosition != destination) {
                if (board.getBoard().get(movedPosition) != null) {
                    return false;
                }
            }
            if (movedPosition == destination) {
                Piece destinationPiece = board.getBoard().get(movedPosition);
                if (destinationPiece != null&& destinationPiece.isSameTeam(destinationPiece)) {
                    return false;
                }
                return true;
            }
        }
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();

        movablePositions.addAll(rookPosition(target));
        movablePositions.addAll(bishopPosition(target));

        return movablePositions;
    }

    private List<Position> rookPosition(Position target) {
        List<Position> movablePositions = new ArrayList<>();
        Vertical targetVertical = target.getVertical();
        Horizontal targetHorizontal = target.getHorizontal();

        for (Horizontal horizontal : Horizontal.values()) {
            movablePositions.add(Position.of(horizontal, targetVertical));
        }
        for (Vertical vertical : Vertical.values()) {
            movablePositions.add(Position.of(targetHorizontal, vertical));
        }

        movablePositions.removeAll(Collections.singletonList(target));

        return movablePositions;
    }

    public List<Position> bishopPosition(Position target) {
        List<Position> movablePositions = new ArrayList<>();

        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.RIGHT_TOP));
        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.LEFT_TOP));
        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.LEFT_BOTTOM));
        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.RIGHT_BOTTOM));

        return movablePositions;
    }

    private List<Position> calculateBishopMovablePositions(Position target, Direction direction) {
        List<Position> result = new ArrayList<>();
        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();
        while (horizontalWeight + direction.getX() >= Board.MIN_BORDER && horizontalWeight + direction.getX() <= Board.MAX_BORDER
                && verticalWeight + direction.getY() >= Board.MIN_BORDER && verticalWeight + direction.getY() <= Board.MAX_BORDER) {
            horizontalWeight += direction.getX();
            verticalWeight += direction.getY();
            result.add(
                    Position.of(Horizontal.findFromWeight(horizontalWeight), Vertical.findFromWeight(verticalWeight))
            );
        }
        return result;
    }

    public int getScore() {
        return score;
    }
}
