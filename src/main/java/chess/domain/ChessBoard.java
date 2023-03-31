package chess.domain;

import chess.domain.path.MovablePaths;
import chess.domain.path.Path;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private static final String WRONG_START_ERROR_MESSAGE = "시작 위치에 말이 없습니다.";
    private static final String OBSTACLE_IN_PATH_ERROR_MESSAGE = "경로에 다른 말이 있어서 이동할 수 없습니다.";
    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";
    private static final String WRONG_PIECE_COLOR_ERROR_MESSAGE = "자신 팀의 말만 이동시킬 수 있습니다.";
    private static final String WRONG_ATTACK_TARGET_ERROR_MESSAGE = "해당 위치의 말은 공격할 수 없습니다.";
    private static final int TOTAL_KING_COUNT = 2;

    private final Map<Position, Piece> piecesByPosition;

    public ChessBoard(final Map<Position, Piece> piecesByPosition) {
        this.piecesByPosition = piecesByPosition;
    }

    public boolean isSourceMoved(final Position source) {
        return hasNothingInDestination(source);
    }

    public void move(final Position source, final Position dest, final TeamColor teamColor) {
        Piece piece = findPieceInStartPosition(source, teamColor);
        progressMove(source, dest, piece);
    }

    private Piece findPieceInStartPosition(final Position start, final TeamColor color) {
        if (!piecesByPosition.containsKey(start)) {
            throw new IllegalArgumentException(WRONG_START_ERROR_MESSAGE);
        }
        Piece piece = piecesByPosition.get(start);
        validatePieceColor(color, piece);
        return piece;
    }

    private void validatePieceColor(final TeamColor color, final Piece piece) {
        if (piece.isDifferentColor(color)) {
            throw new IllegalArgumentException(WRONG_PIECE_COLOR_ERROR_MESSAGE);
        }
    }

    private void progressMove(final Position source, final Position dest, final Piece piece) {
        MovablePaths movablePaths = piece.findMovablePaths(source);
        Path path = movablePaths.findPathContainingPosition(dest);

        validateObstacleInPath(dest, path);
        occupyDestination(source, dest, piece);
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

    private void occupyDestination(final Position source, final Position dest, final Piece piece) {
        if (isWrongMove(source, dest, piece)) {
            throw new IllegalArgumentException(WRONG_DESTINATION_ERROR_MESSAGE);
        }
        if (isWrongAttack(source, dest, piece)) {
            throw new IllegalArgumentException(WRONG_ATTACK_TARGET_ERROR_MESSAGE);
        }
        movePieceToDestination(source, dest, piece);
    }

    private boolean isWrongMove(final Position source, final Position dest, final Piece piece) {
        return hasNothingInDestination(dest) && !piece.canMoveToEmptySquare(source, dest);
    }

    private boolean hasNothingInDestination(final Position dest) {
        return !hasOtherPieceInDestination(dest);
    }

    private boolean isWrongAttack(final Position source, final Position dest, final Piece piece) {
        return hasOtherPieceInDestination(dest) && !piece.canAttack(piecesByPosition.get(dest),
            source, dest);
    }

    public boolean hasOtherPieceInDestination(final Position dest) {
        return piecesByPosition.containsKey(dest);
    }

    private void movePieceToDestination(final Position source, final Position dest,
        final Piece piece) {
        piecesByPosition.put(dest, piece);
        piecesByPosition.remove(source);
    }

    public Map<Position, Piece> piecesByPosition() {
        return new HashMap<>(piecesByPosition);
    }

    public boolean isKingDead() {
        return piecesByPosition.values()
            .stream()
            .filter(Piece::isKing)
            .count() != TOTAL_KING_COUNT;
    }

    public GameResult getGameResult() {
        return GameResult.from(piecesByPosition);
    }

}
