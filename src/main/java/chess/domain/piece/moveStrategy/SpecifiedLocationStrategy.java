package chess.domain.piece.moveStrategy;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpecifiedLocationStrategy extends MoveStrategy {
    @Override
    public List<Position> calculateBoardPosition(Position target, Direction direction) {
        List<Position> result = new ArrayList<>();
        int x = target.getHorizontalWeight() + direction.getX();
        int y = target.getVerticalWeight() + direction.getY();

        if (isInBorder(x, y)) {
            result.add(Position.of(Horizontal.findFromWeight(x), Vertical.findFromWeight(y))            );
        }

        return result;
    }

    private boolean isInBorder(int horizontalWeight, int verticalWeight) {
        return horizontalWeight >= Board.MIN_BORDER && horizontalWeight <= Board.MAX_BORDER
                && verticalWeight >= Board.MIN_BORDER && verticalWeight <= Board.MAX_BORDER;
    }

    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        Piece destinationPiece = board.findPieceFromPosition(destination);
        Piece targetPiece = board.findPieceFromPosition(target);

        if (Objects.isNull(destinationPiece)) {
            return true;
        }
        return targetPiece.isDifferentTeam(destinationPiece);
    }
}
