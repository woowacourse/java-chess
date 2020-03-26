package chess.domain.Piece.bishop;

import chess.domain.Board;
import chess.domain.Piece.Piece;
import chess.domain.Piece.state.Running;
import chess.domain.Piece.team.Team;
import chess.domain.position.Position;

public class Bishop extends Running {
    protected Bishop(Position position, Team team) {
        super(position, team);
    }

    @Override
    protected boolean canNotMove(Position to, Board board) {
        return false;
    }

    @Override
    public Piece move(Position to, Board board) {
        return new Bishop(to, team);
    }
}
