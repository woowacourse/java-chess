package chess.model.piece;

import chess.model.Direction;
import chess.model.Position;
import chess.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bishop extends Piece implements BishopMovable {
    private static final String BLACK_NAME = "B";
    private static final String WHITE_NAME = "b";
    private static final double SCORE = 3D;
    private static final List<Direction> directions = Direction.diagonal();

    public Bishop(Position position, Team team) {
        super(position, team);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return directions.contains(Direction.of(source, target));
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
        throw new IllegalArgumentException("갈수없는 공간입니다.");
    }
}
