package chess.piece;

import chess.Team;
import chess.piece.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Bishop extends Piece implements DiagonalMovable {
    private static final String BLACK_NAME = "B";
    private static final String WHITE_NAME = "b";
    private static final double SCORE = 3D;

    public Bishop(Position position, Team team) {
        super(position, team);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isMovableRange(Position position) {
        return isCorrectDirection(position);
    }

    private boolean isCorrectDirection(Position position) {
        return this.position.isDiagonal(position);
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
        if (position.isPositiveDiagonal(targetPiece.position)) {
            return getPositiveDiagonal(list);
        }
        if (position.isNegativeDiagonal(targetPiece.position)) {
            return getNegativeDiagonal(list);
        }
        throw new IllegalArgumentException("[ERROR] 갈수없는 공간입니다.");
    }
}
