package chess.domain.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.board.Board.*;

public class Queen extends Piece {
    private static final String QUEEN_NAME = "Q";

    public Queen(Team team) {
        super(QUEEN_NAME, team);
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

    private List<Position> bishopPosition(Position target) {
        List<Position> movablePositions = new ArrayList<>();

        movablePositions.addAll(calculateBishopMovablePositions(target, POSITIVE, POSITIVE));
        movablePositions.addAll(calculateBishopMovablePositions(target, NEGATIVE, POSITIVE));
        movablePositions.addAll(calculateBishopMovablePositions(target, NEGATIVE, NEGATIVE));
        movablePositions.addAll(calculateBishopMovablePositions(target, POSITIVE, NEGATIVE));

        return movablePositions;
    }

    private List<Position> calculateBishopMovablePositions(Position target, int horizontalDirection, int verticalDirection) {
        List<Position> result = new ArrayList<>();
        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();
        while (horizontalWeight + horizontalDirection >= MIN_BORDER && horizontalWeight + horizontalDirection <= MAX_BORDER
                && verticalWeight + verticalDirection >= MIN_BORDER && verticalWeight + verticalDirection <= MAX_BORDER) {
            horizontalWeight += horizontalDirection;
            verticalWeight += verticalDirection;
            result.add(
                    Position.of(Horizontal.findFromWeight(horizontalWeight), Vertical.findFromWeight(verticalWeight))
            );
        }
        return result;
    }
}
