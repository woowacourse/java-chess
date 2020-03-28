package chess.domain.piece.king;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.state.Running;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

public class King extends Running {

    public King(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        super(name, position, team, canNotMoveStrategies);
    }

    @Override
    protected boolean canNotMove(Position to, Board board) {
        return false;
    }

    @Override
    public Piece move(Position to, Board board) {
        return new King(name, to, team, canNotMoveStrategies);
    }
}
