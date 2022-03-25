package chess.domain.state;

import chess.domain.Board;
import chess.domain.Location;
import chess.domain.LocationDiff;
import chess.domain.piece.Piece;

public class White extends Running {

    public White(Board board) {
        super(board);
    }

    @Override
    public State move(Location source, Location target) {
        Piece sourcePiece = getBoard().getPiece(source);
        Piece targetPiece = getBoard().getPiece(target);

        checkSourceColor(sourcePiece);
        LocationDiff locationDiff = source.computeDiff(target);

        checkDirection(sourcePiece, locationDiff.computeDirection());
        checkDistance(sourcePiece, locationDiff);
        checkRoute(source, locationDiff);
        checkTarget(targetPiece);

        if (sourcePiece.isPawn() && !Direction.isForward(locationDiff.computeDirection()) && targetPiece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선에 상대 기물이 있을때만 움직일 수 있습니다.");
        }

        getBoard().move(source, target);
        return new Black(getBoard());
    }

    private void checkSourceColor(Piece piece) {
        if (!piece.isWhite()) {
            throw new IllegalArgumentException("[ERROR] 해당 말은 움직일 수 없습니다.");
        }
    }

    private void checkDirection(Piece piece, Direction direction) {
        if (!piece.isMovableDirection(direction)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치로 이동할 수 없습니다.");
        }
    }

    private void checkDistance(Piece piece, LocationDiff locationDiff) {
        if (!piece.isMovableDistance(locationDiff)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치까지 이동할 수 없습니다.");
        }
    }

    private void checkRoute(Location source, LocationDiff locationDiff) {
        for (int i = 0; i < locationDiff.computeDistance() - 1; i++) {
            Location routeLocation = source.add(locationDiff.computeDirection());
            if (!getBoard().isEmpty(routeLocation)) {
                throw new IllegalArgumentException("[ERROR] 해당 경로를 지나갈 수 없습니다. ");
            }
        }
    }

    private void checkTarget(Piece targetPiece) {
        if (targetPiece.isWhite()) {
            throw new IllegalArgumentException("[ERROR] 같은 색의 기물을 잡을 수 없습니다.");
        }
    }
}
