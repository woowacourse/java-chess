package chess.domain.Piece.knight;

import chess.domain.Board;
import chess.domain.Piece.Piece;
import chess.domain.Piece.state.Running;
import chess.domain.Piece.team.Team;
import chess.domain.position.Position;

public class Knight extends Running {
    protected Knight(Position position, Team team) {
        super(position, team);
    }

    @Override
    protected boolean canNotMove(Position to, Board board) {
        return false;
    }

    @Override
    public Piece move(Position to, Board board) {
        return new Knight(to, team);
    }
}
