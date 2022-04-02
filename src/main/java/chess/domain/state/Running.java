package chess.domain.state;

<<<<<<< HEAD
import chess.domain.board.Board;
import chess.domain.board.Direction;
import chess.domain.board.Location;
import chess.domain.board.LocationDiff;
import chess.domain.piece.Piece;
=======
import static chess.domain.state.PieceMovementValidator.*;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.LocationDiff;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.ArrayList;
import java.util.List;
>>>>>>> step1

public abstract class Running implements State {
    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

<<<<<<< HEAD
=======
    @Override
>>>>>>> step1
    public Board getBoard() {
        return board;
    }

    @Override
    public State start() {
<<<<<<< HEAD
        throw new IllegalArgumentException("[ERROR] 게임이 이미 실행중입니다.");
=======
        throw new IllegalStateException("[ERROR] 게임이 이미 실행중입니다.");
>>>>>>> step1
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public State end() {
        return new End(board);
    }

<<<<<<< HEAD
    public void checkMovable(Location source, Location target) {
        LocationDiff locationDiff = source.computeDiff(target);
        Piece sourcePiece = board.getPiece(source);

        checkDirection(sourcePiece, locationDiff.computeDirection());
        checkDistance(sourcePiece, locationDiff);
        checkRoute(source, locationDiff);
=======
    @Override
    public Piece move(Team currentTeam, Location source, Location target) {
        checkMovable(currentTeam, source, target);
        Piece targetPiece = board.getPiece(target);
        board.move(source, target);
        return targetPiece;
    }

    public void checkMovable(Team team, Location source, Location target) {
        LocationDiff locationDiff = source.computeDiff(target);
        Piece sourcePiece = board.getPiece(source);
        Piece targetPiece = board.getPiece(target);

        checkSourceColor(sourcePiece, team);
        checkDirection(sourcePiece, locationDiff.computeDirection());
        checkDistance(sourcePiece, locationDiff);
        checkRoute(getRoutePiece(source, locationDiff));
        checkTarget(targetPiece, team);
>>>>>>> step1

        if (sourcePiece.isPawn()) {
            sourcePiece.checkPawnMovable(locationDiff.computeDirection(), board.getPiece(target));
        }
    }

<<<<<<< HEAD
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
=======
    private List<Piece> getRoutePiece(Location source, LocationDiff locationDiff) {
        Location routeLocation = source.copyOf();
        List<Piece> routePieces = new ArrayList<>();
        for (int i = 0; i < locationDiff.computeDistance() - 1; i++) {
            routeLocation = routeLocation.add(locationDiff.computeDirection());
            routePieces.add(board.getPiece(routeLocation));
        }
        return routePieces;
>>>>>>> step1
    }
}
