package chess.domain.state;

import chess.domain.board.Direction;
import chess.domain.board.LocationDiff;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.List;

public class PieceMovementValidator {

    static void checkSourceColor(Piece piece, Team team) {
        if (!piece.isSameTeam(team)) {
            throw new IllegalArgumentException("[ERROR] 해당 말은 움직일 수 없습니다.");
        }
    }

    static void checkTarget(Piece targetPiece, Team team) {
        if (targetPiece.isSameTeam(team)) {
            throw new IllegalArgumentException("[ERROR] 같은 색의 기물을 잡을 수 없습니다.");
        }
    }

    static void checkDirection(Piece sourcePiece, Direction computeDirection) {
        if (!sourcePiece.isMovableDirection(computeDirection)) {
            throw new IllegalArgumentException("[ERROR] 해당 방향으로 이동할 수 없습니다.");
        }
    }

    static void checkDistance(Piece sourcePiece, LocationDiff locationDiff) {
        if (!sourcePiece.isMovableDistance(locationDiff)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치까지 이동할 수 없습니다.");
        }
    }

    static void checkRoute(List<Piece> routePieces) {
        routePieces.stream()
                .filter(routePiece -> !routePiece.isEmpty())
                .forEach(routePiece -> {
                    throw new IllegalArgumentException("[ERROR] 해당 경로로 이동할 수 없습니다.");
                });
    }
}
