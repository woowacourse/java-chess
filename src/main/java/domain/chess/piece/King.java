package domain.chess.piece;

import domain.chess.Score;

import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private static final Score SCORE = new Score(0);

    private King(String name, int x, int y, boolean isBlack) {
        super(name, SCORE, Position.Of(x, y), isBlack);
    }

    public static King Of(String name, Position position, boolean color) {
        return new King(name, position.getRow(), position.getColumn(), color);
    }

    public static List<King> initialKingPieces() {
        return Arrays.asList(King.Of("K", Position.Of(0, 4), true),
                King.Of("k", Position.Of(7, 4), false));
    }

    @Override
    public King movePosition(Position position) {
        return new King(getName(), position.getRow(), position.getColumn(), isBlack());
    }

    @Override
    public boolean canMove(Piece[][] board, Position endPosition) {
        if (board[endPosition.getRow()][endPosition.getColumn()] != null && isOurTeam(board, endPosition)) return false;

        List<Position> movePositions = Arrays.asList(
                Position.Of(1, 0),
                Position.Of(-1, 0),
                Position.Of(0, -1),
                Position.Of(0, 1),
                Position.Of(-1, 1),
                Position.Of(1, 1),
                Position.Of(-1, -1),
                Position.Of(1, -1)
        );

        int x = position.getRow();
        int y = position.getColumn();
        return movePositions.stream()
                .filter(movePosition ->
                        x + movePosition.getRow() == endPosition.getRow()
                                && y + movePosition.getColumn() == endPosition.getColumn())
                // King의 이동 가능 범위들 중 endPostiion과 일치하는 곳이 있다.
                .findAny()
                .isPresent();
    }
}
