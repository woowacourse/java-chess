package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private static final String WRONG_START_ERROR_MESSAGE = "시작 위치에 말이 없습니다.";
    private static final String OBSTACLE_IN_PATH_ERROR_MESSAGE = "경로에 다른 말이 있어서 이동할 수 없습니다.";
    private static final String WRONG_PAWN_PATH_ERROR_MESSAGE = "폰은 공격 할 때만 대각선으로 이동할 수 있습니다.";
    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";
    private static final String WRONG_PIECE_COLOR_ERROR_MESSAGE = "자신 팀의 말만 이동시킬 수 있습니다.";
    private static final String WRONG_ATTACK_TARGET_ERROR_MESSAGE = "상대 팀의 말만 공격할 수 있습니다.";

    private final Map<Position, Piece> piecesByPosition;

    public ChessBoard() {
        piecesByPosition = InitialPiece.getPiecesWithPosition();
    }

    public void move(final List<Integer> sourcePosition, final List<Integer> destPosition,
        final TeamColor teamColor) {
        Position source = Position.from(sourcePosition);
        Position dest = Position.from(destPosition);

        Piece piece = findPieceInStartPosition(source, teamColor);
        List<Path> movablePaths = piece.findMovablePaths(source);

        for (Path path : movablePaths) {
            if (moveWhenPossible(source, dest, piece, path, teamColor)) {
                return;
            }
        }

        throw new IllegalArgumentException(WRONG_DESTINATION_ERROR_MESSAGE);
    }

    private Piece findPieceInStartPosition(final Position start, final TeamColor color) {
        if (piecesByPosition.containsKey(start)) {
            Piece piece = piecesByPosition.get(start);
            validatePieceColor(color, piece);
            return piece;
        }
        throw new IllegalArgumentException(WRONG_START_ERROR_MESSAGE);
    }

    private void validatePieceColor(final TeamColor color, final Piece piece) {
        if (piece.isSameColor(color)) {
            return;
        }
        throw new IllegalArgumentException(WRONG_PIECE_COLOR_ERROR_MESSAGE);
    }

    private boolean moveWhenPossible(final Position source, final Position dest, final Piece piece,
        final Path path,
        final TeamColor color) {
        if (path.hasPosition(dest)) {
            checkObstacleInPath(dest, path);
            validatePawnAttack(piece, source, dest);
            validateObstacleInDestination(dest, color);

            piecesByPosition.put(dest, piece);
            piecesByPosition.remove(source);
            return true;
        }
        return false;
    }

    private void validateObstacleInDestination(final Position dest, final TeamColor color) {
        if (!piecesByPosition.containsKey(dest)) {
            return;
        }
        if (piecesByPosition.get(dest).isSameColor(color)) {
            throw new IllegalArgumentException(WRONG_ATTACK_TARGET_ERROR_MESSAGE);
        }
    }

    private void validatePawnAttack(final Piece piece, final Position source, final Position dest) {
        if (!piece.isPawn()) {
            return;
        }
        Pawn pawn = (Pawn) piece;
        if (pawn.isAttack(source, dest) && !isOtherPieceInDestination(dest)) {
            throw new IllegalArgumentException(WRONG_PAWN_PATH_ERROR_MESSAGE);
        }
    }

    private boolean isOtherPieceInDestination(final Position dest) {
        return piecesByPosition.containsKey(dest);
    }

    private void checkObstacleInPath(final Position dest, final Path path) {
        List<Position> positions = path.positions();
        for (int index = 0; index < path.findPositionIndex(dest); index++) {
            checkObstacleAtIndex(positions, index);
        }
    }

    private void checkObstacleAtIndex(final List<Position> positions, final int index) {
        if (piecesByPosition.containsKey(positions.get(index))) {
            throw new IllegalArgumentException(OBSTACLE_IN_PATH_ERROR_MESSAGE);
        }
    }

    public Map<Position, Piece> piecesByPosition() {
        return new HashMap<>(piecesByPosition);
    }

}
