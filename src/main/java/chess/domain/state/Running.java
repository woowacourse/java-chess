package chess.domain.state;

import static chess.domain.state.PieceMovementValidator.*;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.LocationDiff;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.ArrayList;
import java.util.List;

public abstract class Running implements State {
    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public State start() {
        throw new IllegalStateException("[ERROR] 게임이 이미 실행중입니다.");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public State end() {
        return new End(board);
    }

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

        if (sourcePiece.isPawn()) {
            sourcePiece.checkPawnMovable(locationDiff.computeDirection(), board.getPiece(target));
        }
    }

    private List<Piece> getRoutePiece(Location source, LocationDiff locationDiff) {
        Location routeLocation = source.copyOf();
        List<Piece> routePieces = new ArrayList<>();
        for (int i = 0; i < locationDiff.computeDistance() - 1; i++) {
            routeLocation = routeLocation.add(locationDiff.computeDirection());
            routePieces.add(board.getPiece(routeLocation));
        }
        return routePieces;
    }
}
