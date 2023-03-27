package chess.model.piece.movement;

import chess.model.position.Distance;

public interface MovementStrategy {

    boolean movable(final Distance distance, final AttackEvaluator attackEvaluator);
}
