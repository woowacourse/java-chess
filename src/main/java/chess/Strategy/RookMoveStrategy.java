package chess.Strategy;

import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RookMoveStrategy extends LinearMoveStrategy {
    @Override
    public List<Position> searchMovablePositions(Position target) {
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
}
