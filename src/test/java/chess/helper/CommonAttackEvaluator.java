package chess.helper;

import chess.model.piece.Camp;
import chess.model.piece.PieceType;
import chess.model.piece.movement.AttackEvaluator;

public class CommonAttackEvaluator {

    protected final AttackEvaluator blackEnemyEvaluator = new AttackEvaluator(Camp.BLACK, Camp.WHITE, PieceType.QUEEN);
    protected final AttackEvaluator blackAllyEvaluator = new AttackEvaluator(Camp.BLACK, Camp.BLACK, PieceType.QUEEN);
    protected final AttackEvaluator whiteEnemyEvaluator = new AttackEvaluator(Camp.WHITE, Camp.BLACK, PieceType.QUEEN);
    protected final AttackEvaluator whiteAllyEvaluator = new AttackEvaluator(Camp.WHITE, Camp.WHITE, PieceType.QUEEN);
    protected final AttackEvaluator blackEmptyEvaluator = new AttackEvaluator(Camp.BLACK, Camp.WHITE, PieceType.EMPTY);
    protected final AttackEvaluator whiteEmptyEvaluator = new AttackEvaluator(Camp.WHITE, Camp.BLACK, PieceType.EMPTY);
}
