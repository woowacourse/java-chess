package chess.domain;

import chess.domain.path.MovablePaths;
import chess.domain.path.Path;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.HashMap;
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

    public void move(final Position source, final Position dest, final TeamColor teamColor) {
        Piece piece = findPieceInStartPosition(source, teamColor);
        progressMove(teamColor, source, dest, piece);
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

    private void progressMove(final TeamColor teamColor, final Position source, final Position dest,
        final Piece piece) {
        MovablePaths movablePaths = piece.findMovablePaths(source);
        Path path = movablePaths.findPathContainingPosition(dest);

        if (isMoveSuccess(teamColor, source, dest, piece, path)) {
            return;
        }
        throw new IllegalArgumentException(WRONG_DESTINATION_ERROR_MESSAGE);
    }

    private boolean isMoveSuccess(final TeamColor teamColor, final Position source,
        final Position dest, Piece piece, final Path path) {
        validateObstacleInPath(dest, path);
        validatePawnAttack(piece, source, dest);
        validateObstacleInDestination(dest, teamColor);
        movePieceToDestination(source, dest, piece);
        return true;
    }

    private void validateObstacleInPath(final Position dest, final Path path) {
        for (int index = 0; index < path.findIndexByPosition(dest); index++) {
            checkObstacleAtIndex(path, index);
        }
    }

    private void checkObstacleAtIndex(final Path path, final int index) {
        if (piecesByPosition.containsKey(path.findPositionByIndex(index))) {
            throw new IllegalArgumentException(OBSTACLE_IN_PATH_ERROR_MESSAGE);
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
        if (!pawn.isAttack(source, dest) && isOtherPieceInDestination(dest)) {
            throw new IllegalArgumentException(WRONG_DESTINATION_ERROR_MESSAGE);
        }
    }

    private boolean isOtherPieceInDestination(final Position dest) {
        return piecesByPosition.containsKey(dest);
    }

    private void validateObstacleInDestination(final Position dest, final TeamColor color) {
        if (!piecesByPosition.containsKey(dest)) {
            return;
        }
        if (piecesByPosition.get(dest).isSameColor(color)) {
            throw new IllegalArgumentException(WRONG_ATTACK_TARGET_ERROR_MESSAGE);
        }
    }

    private void movePieceToDestination(final Position source, final Position dest,
        final Piece piece) {
        piecesByPosition.put(dest, piece);
        piecesByPosition.remove(source);
    }

    public Map<Position, Piece> piecesByPosition() {
        return new HashMap<>(piecesByPosition);
    }

}
