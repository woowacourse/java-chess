package chess.domain;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoard {

    private static final String WRONG_START_ERROR_MESSAGE = "시작 위치에 말이 없습니다.";
    private static final String OBSTACLE_IN_PATH_ERROR_MESSAGE = "경로에 다른 말이 있어서 이동할 수 없습니다.";
    private static final String WRONG_PIECE_COLOR_ERROR_MESSAGE = "자신 팀의 말만 이동시킬 수 있습니다.";
    private static final String WRONG_ATTACK_ERROR_MESSAGE = "선택한 말로 공격할 수 없습니다.";
    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";

    private final Map<Position, Piece> piecesByPosition = PieceInitializer.createPiecesWithPosition();
    private final CampSwitcher campSwitcher;
    private Camp currentTurnCamp;

    public ChessBoard(final Camp currentTurnCamp, final CampSwitcher campSwitcher) {
        this.currentTurnCamp = currentTurnCamp;
        this.campSwitcher = campSwitcher;
    }

    public void move(Position source, Position destination) {
        Piece movingPiece = findPieceAtSourcePosition(source);
        CheckablePaths checkablePaths = movingPiece.findCheckablePaths(source);
        Path pathToDestination = checkablePaths.findPathContainingPosition(destination);
        progressIfPossible(pathToDestination, source, destination, movingPiece);
        switchCampTurn();
    }

    private Piece findPieceAtSourcePosition(final Position source) {
        validatePieceAtSourcePosition(source);
        Piece piece = piecesByPosition.get(source);
        validatePieceColor(piece);
        return piece;
    }

    private void validatePieceAtSourcePosition(final Position start) {
        if (piecesByPosition.containsKey(start)) {
            return;
        }
        throw new IllegalArgumentException(WRONG_START_ERROR_MESSAGE);
    }

    private void validatePieceColor(final Piece piece) {
        if (piece.isSameCamp(currentTurnCamp)) {
            return;
        }
        throw new IllegalArgumentException(WRONG_PIECE_COLOR_ERROR_MESSAGE);
    }

    private void progressIfPossible(final Path pathToDestination, final Position source, final Position destination,
                                    final Piece movingPiece) {
        validateObstacleInPath(pathToDestination, destination);
        validateMoveToEmpty(source, destination, movingPiece);
        validateAttack(source, destination, movingPiece);
        piecesByPosition.put(destination, movingPiece);
        piecesByPosition.remove(source);
    }

    private void validateMoveToEmpty(final Position source, final Position destination, final Piece movingPiece) {
        if (isEmptyPosition(destination) && !movingPiece.canMoveToEmpty(source, destination)) {
            throw new IllegalArgumentException(WRONG_DESTINATION_ERROR_MESSAGE);
        }
    }

    private void validateObstacleInPath(final Path path, final Position destination) {
        if (hasObstacleInPath(path, destination)) {
            throw new IllegalArgumentException(OBSTACLE_IN_PATH_ERROR_MESSAGE);
        }
    }

    private boolean hasObstacleInPath(final Path path, final Position destination) {
        List<Position> positions = path.positions();
        return IntStream.range(0, path.findPositionIndex(destination))
                .anyMatch(index -> piecesByPosition.containsKey(positions.get(index)));
    }

    private void validateAttack(final Position source, final Position destination, final Piece movingPiece) {
        if (piecesByPosition.containsKey(destination) && !movingPiece.canAttack(source, destination,
                piecesByPosition.get(destination))) {
            throw new IllegalArgumentException(WRONG_ATTACK_ERROR_MESSAGE);
        }
    }

    private boolean isEmptyPosition(final Position destination) {
        return !piecesByPosition.containsKey(destination);
    }

    private void switchCampTurn() {
        currentTurnCamp = campSwitcher.switchTurn(currentTurnCamp);
    }

    public Map<Position, Piece> piecesByPosition() {
        return new HashMap<>(piecesByPosition);
    }

}
