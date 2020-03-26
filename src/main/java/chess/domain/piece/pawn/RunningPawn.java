package chess.domain.piece.pawn;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.state.Running;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

public class RunningPawn extends Running {

    protected RunningPawn(String name, Position position, Team team) {
        super(name, position, team);
    }

    //todo: add logic
    @Override
    protected boolean canNotMove(Position to, Board board) {
        return false;
    }

    //todo: add logic
    @Override
    public Piece move(Position to, Board board) {
        return new RunningPawn(name, to, team);
    }
}
