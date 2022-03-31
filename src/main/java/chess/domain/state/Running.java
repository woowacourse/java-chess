package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Direction;
import chess.domain.board.Location;
import chess.domain.board.LocationDiff;
import chess.domain.piece.Piece;

public abstract class Running implements State {
    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException("[ERROR] 게임이 이미 실행중입니다.");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public State end() {
        return new End(board);
    }

    public void checkMovable(Location source, Location target) {
        LocationDiff locationDiff = source.computeDiff(target);
        Piece sourcePiece = board.getPiece(source);

        checkDirection(sourcePiece, locationDiff.computeDirection());
        checkDistance(sourcePiece, locationDiff);
        checkRoute(source, locationDiff);

        if (sourcePiece.isPawn()) {
            sourcePiece.checkPawnMovable(locationDiff.computeDirection(), board.getPiece(target));
        }
    }

    private void checkDirection(Piece sourcePiece, Direction computeDirection) {
        if (!sourcePiece.isMovableDirection(computeDirection)) {
            throw new IllegalArgumentException("[ERROR] 해당 방향으로 이동할 수 없습니다.");
        }
    }

    private void checkDistance(Piece sourcePiece, LocationDiff locationDiff) {
        if (!sourcePiece.isMovableDistance(locationDiff)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치까지 이동할 수 없습니다.");
        }
    }

    private void checkRoute(Location source, LocationDiff locationDiff) {
        Location routeLocation = source.copyOf();
        for (int i = 0; i < locationDiff.computeDistance() - 1; i++) {
            routeLocation = routeLocation.add(locationDiff.computeDirection());
            if (!getBoard().isEmpty(routeLocation)) {
                throw new IllegalArgumentException("[ERROR] 해당 경로를 지나갈 수 없습니다. ");
            }
        }
    }
}
