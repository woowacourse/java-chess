package chess.piece;

import chess.Team;
import chess.piece.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Queen extends Piece implements RookMovable, BishopMovable {
    private static final String BLACK_NAME = "Q";
    private static final String WHITE_NAME = "q";
    private static final double SCORE = 9D;

    public Queen(Position position, Team team) {
        super(position, team);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isMovable(Position position) {
        return isCorrectDirection(position);
    }

    @Override
    public String getName() {
        if (Team.BLACK.equals(team)) {
            return BLACK_NAME;
        }
        return WHITE_NAME;
    }

    @Override
    public List<Position> getIntervalPosition(Piece targetPiece) {
        List<Piece> list = new ArrayList<>(List.of(this, targetPiece));
        Collections.sort(list);
        if (position.isHorizontal(targetPiece.position)) {
            return getHorizontalPositions(list);
        }
        if (position.isVertical(targetPiece.position)) {
            return getVerticalPositions(list);
        }
        if (position.isPositiveDiagonal(targetPiece.position)) {
            return getPositiveDiagonal(list);
        }
        if (position.isNegativeDiagonal(targetPiece.position)) {
            return getNegativeDiagonal(list);
        }
        throw new IllegalArgumentException("[ERROR] 갈 수 없는 공간입니다.");
    }

    private boolean isCorrectDirection(Position position) {
        return this.position.isVertical(position) ||
                this.position.isHorizontal(position) ||
                this.position.isDiagonal(position);
    }
}
