package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class King extends Piece {
    private static final String KING_NAME = "K";
    private static final double SCORE = 0;

    public King(Team team) {
        super(KING_NAME, team, SCORE);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();

        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();

        for (Direction direction : Direction.values()) {
            int changedHorizontalWeight = horizontalWeight + direction.getX();
            int changedVerticalWeight = verticalWeight + direction.getY();
            movablePositions.addAll(calculateBoardPosition(changedHorizontalWeight, changedVerticalWeight));
        }

        return movablePositions;
    }

    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        Piece destinationPiece = board.getBoard().get(destination);
        Piece targetPiece = board.getBoard().get(target);

        if (Objects.isNull(destinationPiece)) {
            return true;
        }
        return !targetPiece.isSameTeam(destinationPiece);
    }

    private List<Position> calculateBoardPosition(int changedHorizontalWeight, int changedVerticalWeight) {
        List<Position> result = new ArrayList<>();
        if (isInBorder(changedHorizontalWeight, changedVerticalWeight)) {
            result.add(Position.of(
                    Horizontal.findFromWeight(changedHorizontalWeight),
                    Vertical.findFromWeight(changedVerticalWeight))
            );
        }
        return result;
    }

    private boolean isInBorder(int horizontalWeight, int verticalWeight) {
        return horizontalWeight >= Board.MIN_BORDER && horizontalWeight <= Board.MAX_BORDER
                && verticalWeight >= Board.MIN_BORDER && verticalWeight <= Board.MAX_BORDER;
    }
}
