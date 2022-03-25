package chess.piece;

import chess.Position;
import chess.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bishop extends Piece {
    private static final String BLACK_NAME = "B";
    private static final String WHITE_NAME = "b";

    public Bishop(Position position, Team team) {
        super(position, team);
    }

    @Override
    public boolean isMovable(Position position) {
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
        List<Position> positions = new ArrayList<>();
        List<Piece> list = new ArrayList<>(List.of(this, targetPiece));
        Collections.sort(list); // rank 큰게 먼저
        // rank 같다면, file이 작은게 먼저
        if (position.isPositiveDiagonal(targetPiece.position)) {
            return getPositiveDiagonal(positions, list);
        }
        if (position.isNegativeDiagonal(targetPiece.position)) {
            return getNegativeDiagonal(positions, list);
        }
        throw new IllegalArgumentException("갈수없는 공간입니다.");
    }

    private List<Position> getPositiveDiagonal(List<Position> positions, List<Piece> list) {
        Piece rightUpper = list.get(0);
        Piece leftUnder = list.get(1);
        int distance = rightUpper.position.getFile().absMinus(leftUnder.position.getFile());
        for (int i = 1; i < distance; i++) {
            positions.add(leftUnder.position.getPositiveDiagonalPosition(i));
        }
        return positions;
    }

    private List<Position> getNegativeDiagonal(List<Position> positions, List<Piece> list) {
        Piece leftUpper = list.get(0);
        Piece rightUnder = list.get(1);
        int distance = rightUnder.position.getFile().absMinus(leftUpper.position.getFile());
        for (int i = 1; i < distance; i++) {
            positions.add(leftUpper.position.getNegativeDiagonalPosition(i));
        }
        return positions;
    }
}
