package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.dto.ChessBoardStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoard {

    private static final String WRONG_START_ERROR_MESSAGE = "시작 위치에 말이 없습니다.";
    private static final String OBSTACLE_IN_PATH_ERROR_MESSAGE = "경로에 다른 말이 있어서 이동할 수 없습니다.";
    private static final String WRONG_PIECE_COLOR_ERROR_MESSAGE = "자신 팀의 말만 이동시킬 수 있습니다.";
    private static final String WRONG_ATTACK_ERROR_MESSAGE = "선택한 말로 공격할 수 없습니다.";
    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";
    private final Map<Position, Piece> piecesByPosition;
    private boolean isOver;
    private Camp currentTurnCamp;

    public ChessBoard() {
        this.piecesByPosition = PieceInitializer.createPiecesWithPosition();
        this.currentTurnCamp = Camp.WHITE;
    }

    public ChessBoard(final Map<Position, Piece> piecesByPosition) {
        this.piecesByPosition = new HashMap<>(piecesByPosition);
        this.isOver = false;
        this.currentTurnCamp = Camp.WHITE;
    }

    public ChessBoard(final Map<Position, Piece> piecesByPosition, final ChessBoardStatus boardStatus) {
        this.piecesByPosition = piecesByPosition;
        this.isOver = boardStatus.isOver();
        this.currentTurnCamp = boardStatus.getCurrentTurn();
    }

    public void move(Position source, Position destination) {
        Piece movingPiece = findPieceAtSourcePosition(source);
        CheckablePaths checkablePaths = movingPiece.findCheckablePaths(source);
        Path pathToDestination = checkablePaths.findPathContainingPosition(destination);
        boolean isKingChecked = progressIfPossible(pathToDestination, source, destination, movingPiece);
        if (!isKingChecked) {
            switchCampTurn();
        }
        this.isOver = isKingChecked;
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

    private boolean progressIfPossible(final Path pathToDestination, final Position source, final Position destination,
                                       final Piece movingPiece) {
        validateObstacle(pathToDestination, destination);
        validateMove(source, destination, movingPiece);
        boolean isKingAttacked = isKingAt(destination);
        piecesByPosition.put(destination, movingPiece);
        piecesByPosition.put(source, Empty.getInstance());
        return isKingAttacked;
    }

    private boolean isKingAt(Position position) {
        Piece target = piecesByPosition.get(position);
        return target.getType() == PieceType.KING;
    }

    private void validateObstacle(final Path path, final Position destination) {
        if (hasObstacleInPath(path, destination)) {
            throw new IllegalArgumentException(OBSTACLE_IN_PATH_ERROR_MESSAGE);
        }
    }

    private boolean hasObstacleInPath(final Path path, final Position destination) {
        return IntStream.range(0, path.findPositionIndex(destination))
                .anyMatch(index -> !isEmptyPosition((path.findByIndex(index))));
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
        return piecesByPosition.get(position).getType() == PieceType.EMPTY;
    }

    private void switchCampTurn() {
        currentTurnCamp = currentTurnCamp.transfer();
    }

    public double calculateScoreByCamp(Camp camp) {
        return positionsByPiece().entrySet()
                .stream()
                .filter(entry -> entry.getKey().isSameCamp(camp))
                .map(entry -> calculatePieceScore(entry.getKey(), entry.getValue()))
                .reduce(0.0, Double::sum);
    }

    private double calculatePieceScore(Piece piece, List<Position> existingPositions) {
        return piece.sumPointsOf(existingPositions);
    }

    private Map<Piece, List<Position>> positionsByPiece() {
        return piecesByPosition.keySet()
                .stream()
                .collect(Collectors.groupingBy(piecesByPosition::get));
    }

    public ChessBoardStatus status() {
        return new ChessBoardStatus(currentTurnCamp, isOver);
    }

    public Map<Position, Piece> piecesByPosition() {
        return new HashMap<>(piecesByPosition);
    }

}
