package chess.domain.piece.state;

import chess.domain.board.Board;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

public abstract class NotPawn extends Initialized {
    protected NotPawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
        super(name, position, team, canNotMoveStrategies, score);
    }

    @Override
    public Score calculateScore(Board board) {
        return score;
    }
}
