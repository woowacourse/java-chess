package chess.Strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Objects;

public abstract class SpecifiedMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        Piece destinationPiece = board.getBoard().get(destination);
        Piece targetPiece = board.getBoard().get(target);

        if (Objects.isNull(destinationPiece)) {
            return true;
        }
        return !targetPiece.isSameTeam(destinationPiece);
    }

    public boolean isInBorder(int horizontalWeight, int verticalWeight) {
        return horizontalWeight >= Board.MIN_BORDER && horizontalWeight <= Board.MAX_BORDER
                && verticalWeight >= Board.MIN_BORDER && verticalWeight <= Board.MAX_BORDER;
    }
}
