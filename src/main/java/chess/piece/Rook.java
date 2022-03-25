package chess.piece;

import chess.Position;
import chess.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {
    private static final String BLACK_NAME = "R";
    private static final String WHITE_NAME = "r";

    public Rook(Position position, Team team) {
        super(position, team);
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

    private List<Position> getVerticalPositions(List<Position> positions, List<Piece> list) {
        Piece up = list.get(0);
        Piece down = list.get(1);
        int distance = up.position.getRank().absMinus(down.position.getRank());
        for (int i = 1; i < distance; i++) {
            positions.add(down.position.getUpVerticalPosition(i));
        }
        return positions;
    }

    private List<Position> getHorizontalPositions(List<Position> positions, List<Piece> list) {
        Piece left = list.get(0);
        Piece right = list.get(1);
        int distance = right.position.getFile().absMinus(left.position.getFile());
        for (int i = 1; i < distance; i++) {
            positions.add(left.position.getRightHorizontalPosition(i));
        }
        return positions;
    }

    private boolean isCorrectDirection(Position position) {
        return this.position.isVertical(position) || this.position.isHorizontal(position);
    }
}
