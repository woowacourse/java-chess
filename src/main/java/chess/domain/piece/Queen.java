package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Team;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.QueenMoveStrategy;

public final class Queen extends Piece {

    public Queen(Team team) {
        super(new Name("Q"), team, new QueenMoveStrategy());
    }

    @Override
    public boolean canMove(Piece targetPiece, Position from, Position to) {
        return moveStrategy.isValidateCanMove(team, from, to);
    }
}
