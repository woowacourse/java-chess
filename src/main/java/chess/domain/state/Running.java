package chess.domain.state;

import chess.domain.Board;
import chess.domain.location.Direction;
import chess.domain.location.Location;
import chess.domain.location.LocationDiff;
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
        return new End(getBoard());
    }

    @Override
    public State move(Location source, Location target) {
        Piece sourcePiece = board.getPiece(source);
        Piece targetPiece = board.getPiece(target);
        LocationDiff locationDiff = source.computeDiff(target);
        checkMovable(sourcePiece, targetPiece, locationDiff);
        checkRoute(source, locationDiff);
        board.move(source, target);

        if (targetPiece.isKing()) {
            return end();
        }
        return getOpposingTeam();
    }

    private void checkMovable(Piece sourcePiece, Piece targetPiece, LocationDiff locationDiff) {
        checkSourceColor(sourcePiece);
        checkDirection(sourcePiece, locationDiff.computeDirection());
        checkDistance(sourcePiece, locationDiff);
        checkTarget(targetPiece);
        checkPawn(sourcePiece, targetPiece, locationDiff);
    }

    abstract void checkSourceColor(Piece piece);

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
    abstract void checkTarget(Piece piece);

    private void checkPawn(Piece sourcePiece, Piece targetPiece, LocationDiff locationDiff) {
        if (sourcePiece.isPawn()){
            checkPawnTargetLocation(targetPiece, locationDiff);
        }
    }

    private void checkPawnTargetLocation(Piece targetPiece, LocationDiff locationDiff) {
        if (!Direction.isForward(locationDiff.computeDirection()) && !targetPiece.isBlack()) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선에 상대 기물이 있을때만 움직일 수 있습니다.");
        }
        if (Direction.isForward(locationDiff.computeDirection()) && !targetPiece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 폰은 앞에 기물이 존재하면 직진할 수 없습니다.");
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

    abstract Running getOpposingTeam();
}
