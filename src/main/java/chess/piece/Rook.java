package chess.piece;

import chess.Team;
import chess.piece.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Rook extends Piece implements RookMovable {
    private static final String BLACK_NAME = "R";
    private static final String WHITE_NAME = "r";
    private static final double SCORE = 5D;

    public Rook(Position position, Team team) {
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
        Collections.sort(list); // rank 큰게 먼저
        // rank 같다면, file이 작은게 먼저
        if (position.isHorizontal(targetPiece.position)) {
            return getHorizontalPositions(list);
        }
        if (position.isVertical(targetPiece.position)) {
            return getVerticalPositions(list);
        }
        throw new IllegalArgumentException("[ERROR] 갈 수 없는 공간입니다.");
    }

    private boolean isCorrectDirection(Position position) {
        return this.position.isVertical(position) || this.position.isHorizontal(position);
    }

}
