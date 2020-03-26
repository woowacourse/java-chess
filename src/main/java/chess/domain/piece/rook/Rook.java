package chess.domain.piece.rook;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.state.Running;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

public class Rook extends Running {

    public Rook(String name, Position position, Team team) {
        super(name, position, team);
    }

    @Override
    protected boolean canNotMove(Position to, Board board) {
        return false;
    }

    @Override
    public Piece move(Position to, Board board) {
        return new Rook(name, to, team);
    }
}
