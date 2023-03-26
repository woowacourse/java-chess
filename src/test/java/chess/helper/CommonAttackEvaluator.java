package chess.helper;

import chess.model.piece.Camp;
import chess.model.piece.PieceType;
import chess.model.piece.movement.AttackEvaluator;

public class CommonAttackEvaluator {

    protected final AttackEvaluator blackEnemyEvaluator = new AttackEvaluator(Camp.BLACK, Camp.WHITE, false);
    protected final AttackEvaluator blackAllyEvaluator = new AttackEvaluator(Camp.BLACK, Camp.BLACK, false);
    protected final AttackEvaluator whiteEnemyEvaluator = new AttackEvaluator(Camp.WHITE, Camp.BLACK, false);
    protected final AttackEvaluator whiteAllyEvaluator = new AttackEvaluator(Camp.WHITE, Camp.WHITE, false);
    protected final AttackEvaluator blackEmptyEvaluator = new AttackEvaluator(Camp.BLACK, Camp.WHITE, true);
    protected final AttackEvaluator whiteEmptyEvaluator = new AttackEvaluator(Camp.WHITE, Camp.BLACK, true);
}
