package chess.domain.piece.knight;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.state.Running;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

public class Knight extends Running {

    public Knight(String name, Position position, Team team) {
        super(name, position, team);
    }

    @Override
    protected boolean canNotMove(Position to, Board board) {
        return false;
    }

    @Override
    public Piece move(Position to, Board board) {
        return new Knight(name, to, team);
    }
}
