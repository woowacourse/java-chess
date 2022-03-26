package chess.piece;

import chess.Position;
import chess.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece implements RookMovable {
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
        List<Position> positions = new ArrayList<>();
        List<Piece> list = new ArrayList<>(List.of(this, targetPiece));
        Collections.sort(list); // rank 큰게 먼저
        // rank 같다면, file이 작은게 먼저
        if (position.isHorizontal(targetPiece.position)) {
            return getHorizontalPositions(positions, list);
        }
        if (position.isVertical(targetPiece.position)) {
            return getVerticalPositions(positions, list);
        }
        throw new IllegalArgumentException("갈수없는 공간입니다.");
    }

    private boolean isCorrectDirection(Position position) {
        return this.position.isVertical(position) || this.position.isHorizontal(position);
    }

    @Override
    public boolean isKill(Piece piece) {
        return false;
    }
}
