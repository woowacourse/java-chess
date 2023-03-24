package chess.domain;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoard {

    private static final String WRONG_START_ERROR_MESSAGE = "시작 위치에 말이 없습니다.";
    private static final String OBSTACLE_IN_PATH_ERROR_MESSAGE = "경로에 다른 말이 있어서 이동할 수 없습니다.";
    private static final String WRONG_PIECE_COLOR_ERROR_MESSAGE = "자신 팀의 말만 이동시킬 수 있습니다.";
    private static final String WRONG_ATTACK_ERROR_MESSAGE = "선택한 말로 공격할 수 없습니다.";
    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";
    private final Map<Position, Piece> piecesByPosition = PieceInitializer.createPiecesWithPosition();
    private Camp currentTurnCamp;

    public ChessBoard() {
        this.currentTurnCamp = Camp.WHITE;
    }

    public void move(Position source, Position destination) {
        Piece movingPiece = findPieceAtSourcePosition(source);
        CheckablePaths checkablePaths = movingPiece.findCheckablePaths(source);
        Path pathToDestination = checkablePaths.findPathContainingPosition(destination);
        progressIfPossible(pathToDestination, source, destination, movingPiece);
        switchCampTurn();
    }

    private Piece findPieceAtSourcePosition(final Position source) {
        if (isEmptyPosition(source)) {
            throw new IllegalArgumentException(WRONG_START_ERROR_MESSAGE);
        }
        Piece piece = piecesByPosition.get(source);
        if (piece.isDifferentCamp(currentTurnCamp)) {
            throw new IllegalArgumentException(WRONG_PIECE_COLOR_ERROR_MESSAGE);
        }
        return piece;
    }

    private void progressIfPossible(final Path pathToDestination, final Position source, final Position destination,
                                    final Piece movingPiece) {
        validateObstacle(pathToDestination, destination);
        validateMove(source, destination, movingPiece);
        piecesByPosition.put(destination, movingPiece);
        piecesByPosition.remove(source);
    }

    private void validateObstacle(final Path path, final Position destination) {
        if (hasObstacleInPath(path, destination)) {
            throw new IllegalArgumentException(OBSTACLE_IN_PATH_ERROR_MESSAGE);
        }
    }

    private boolean hasObstacleInPath(final Path path, final Position destination) {
        return IntStream.range(0, path.findPositionIndex(destination))
                .anyMatch(index -> piecesByPosition.containsKey((path.findByIndex(index))));
    }

    private void validateMove(final Position source, final Position destination, final Piece movingPiece) {
        if (isEmptyPosition(destination)) {
            validateMoveToEmpty(source, destination, movingPiece);
            return;
        }
        validateAttack(source, destination, movingPiece);
    }

    private void validateMoveToEmpty(final Position source, final Position destination, final Piece movingPiece) {
        if (!movingPiece.canMoveToEmpty(source, destination)) {
            throw new IllegalArgumentException(WRONG_DESTINATION_ERROR_MESSAGE);
        }
    }

    private void validateAttack(final Position source, final Position destination, final Piece movingPiece) {
        if (notAbleToAttack(source, destination, movingPiece)) {
            throw new IllegalArgumentException(WRONG_ATTACK_ERROR_MESSAGE);
        }
    }

    private boolean notAbleToAttack(final Position source, final Position destination, final Piece movingPiece) {
        return !movingPiece.canAttack(source, destination, piecesByPosition.get(destination));
    }

    private boolean isEmptyPosition(final Position position) {
        return !piecesByPosition.containsKey(position);
    }

    private void switchCampTurn() {
        currentTurnCamp = currentTurnCamp.transfer();
    }

    public Map<Position, Piece> piecesByPosition() {
        return new HashMap<>(piecesByPosition);
    }

}
