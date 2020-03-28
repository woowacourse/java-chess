package chess.domain.piece.rook;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.state.Running;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

public class Rook extends Running {

    public Rook(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        super(name, position, team, canNotMoveStrategies);
    }

    @Override
    protected boolean canNotMove(Position to, Board board) {
        return false;
    }

    @Override
    public Piece move(Position to, Board board) {
        return new Rook(name, to, team, canNotMoveStrategies);
    }
}
