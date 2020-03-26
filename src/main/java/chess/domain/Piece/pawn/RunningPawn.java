package chess.domain.Piece.pawn;

import chess.domain.Board;
import chess.domain.Piece.Piece;
import chess.domain.Piece.state.Running;
import chess.domain.Piece.team.Team;
import chess.domain.position.Position;

public class RunningPawn extends Running {
    RunningPawn(Position position, Team team) {
        super(position, team);
    }

    //todo: add logic
    @Override
    protected boolean canNotMove(Position to, Board board) {
        return false;
    }

    //todo: add logic
    @Override
    public Piece move(Position to, Board board) {
        return new RunningPawn(to, team);
    }
}
