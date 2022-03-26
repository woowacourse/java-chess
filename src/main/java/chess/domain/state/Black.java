package chess.domain.state;

import chess.domain.Board;
import chess.domain.Location;
import chess.domain.LocationDiff;
import chess.domain.TeamScore;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class Black extends Running {
    public Black(Board board) {
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

        if (sourcePiece.isPawn()){
            if (!Direction.isForward(locationDiff.computeDirection()) && !targetPiece.isWhite()) {
                throw new IllegalArgumentException("[ERROR] 폰은 대각선에 상대 기물이 있을때만 움직일 수 있습니다.");
            }
            if (Direction.isForward(locationDiff.computeDirection()) && !targetPiece.isEmpty()) {
                throw new IllegalArgumentException("[ERROR] 폰은 앞에 기물이 존재하면 직진할 수 없습니다.");
            }

        }

        getBoard().move(source, target);
        if (targetPiece.isKing()) {
            System.out.println("블랙이 이김");
            return end();
        }
        return new White(getBoard());
    }

    @Override
    public TeamScore getScore() {
        double score = getBoard().computeTotalScore(Team.BLACK);
        return new TeamScore(Team.BLACK, score);
    }

    private void checkSourceColor(Piece piece) {
        if (!piece.isBlack()) {
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
        Location routeLocation = source.copyOf();
        for (int i = 0; i < locationDiff.computeDistance() - 1; i++) {
            routeLocation = routeLocation.add(locationDiff.computeDirection());
            if (!getBoard().isEmpty(routeLocation)) {
                throw new IllegalArgumentException("[ERROR] 해당 경로를 지나갈 수 없습니다. ");
            }
        }
    }

    private void checkTarget(Piece targetPiece) {
        if (targetPiece.isBlack()) {
            throw new IllegalArgumentException("[ERROR] 같은 색의 기물을 잡을 수 없습니다.");
        }
    }
}
