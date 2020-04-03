package chess.domain.piece.state.piece;

import chess.domain.board.Board;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.team.Team;
import chess.domain.piece.position.Position;

import java.util.List;

public abstract class NotPawn extends Initialized {
    protected NotPawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
        super(name, position, team, canNotMoveStrategies, score);
    }

    public NotPawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score, MoveType moveType) {
        super(name, position, team, canNotMoveStrategies, score, moveType);
    }


    @Override
    public Score calculateScore(Board board) {
        return score;
    }
}
