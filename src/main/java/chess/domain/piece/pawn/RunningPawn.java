package chess.domain.piece.pawn;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.state.Running;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

public class RunningPawn extends Running {


    protected RunningPawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        super(name, position, team, canNotMoveStrategies);
    }

    //todo: add logic
    @Override
    protected boolean canNotMove(Position to, Board board) {
        return false;
    }

    //todo: add logic
    @Override
    //todo: use factory
    public Piece move(Position to, Board board) {
        return new RunningPawn(name, to, team, canNotMoveStrategies);
    }
}
