package chess.domain.player;

import chess.domain.board.Board;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.Point;

public class Player {

    private final Board board;
    private final Team team;

    public Player(Team team, Board board) {
        this.board = board;
        this.team = team;
    }

    public void move(Point departure, Point destination) {
        Piece currentPiece = board.get(departure);
        Piece targetPiece = board.get(destination);
        validateMove(departure, destination, currentPiece, targetPiece);

        board.move(departure, destination);
    }

    private void validateMove(Point currentPoint, Point destination, Piece currentPiece,
            Piece targetPiece) {
        validateMyPiece(currentPiece);
        validateMovablePoint(currentPoint, destination, currentPiece, targetPiece);
        validateDestination(destination);
        validateMovableRoute(currentPoint, destination);
    }

    private void validateMyPiece(Piece currentPiece) {
        if (!currentPiece.isSameTeam(team)) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
    }

    private void validateMovablePoint(Point currentPoint, Point destination, Piece currentPiece, Piece targetPiece) {
        if (!currentPiece.isMovable(currentPoint, destination, targetPiece)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 있는 위치가 아닙니다.");
        }
    }

    private void validateDestination(Point destination) {
        Piece nextPiece = board.get(destination);
        if (Piece.empty() != nextPiece && nextPiece.isSameTeam(team)) {
            throw new IllegalArgumentException("이동하려는 위치에 이미 자신의 기물이 있을 수 없습니다.");
        }
    }

    private void validateMovableRoute(Point currentPoint, Point destination) {
        Direction unitDirection = currentPoint.findUnitDirection(destination);
        Point nextPoint = currentPoint.move(unitDirection);
        while (nextPoint != destination) {
            Piece nextPiece = board.get(nextPoint);
            if (Piece.empty() != nextPiece) {
                throw new IllegalArgumentException("이동 경로에 기물이 존재하여 이동할 수 없습니다.");
            }
            nextPoint = nextPoint.move(unitDirection);
        }
    }
}
