package chess.model;

import static chess.model.PieceColor.*;

import java.util.Map;

import chess.model.boardinitializer.BoardInitializer;
import chess.model.piece.EmptyPiece;
import chess.model.piece.Piece;

public class Board {

    static final String ERROR_SOURCE_PIECE_EMPTY = "[ERROR] 출발 위치에는 말이 있어야 합니다.";
    static final String ERROR_NOT_CORRECT_TURN = "[ERROR] 현재 올바르지 않은 팀 선택입니다.";
    static final String ERROR_NOT_MOVABLE = "[ERROR] 이동할 수 없는 위치입니다.";
    static final String ERROR_NOT_MOVABLE_CHESS_FINISHED = "[ERROR] 체스 게임이 종료되어 이동할 수 없습니다.";
    private static final Piece EMPTY_PIECE = EmptyPiece.of(EMPTY);

    private final Map<Position, Piece> values;
    private final TurnDecider turnDecider;

    public Board(TurnDecider turnDecider, BoardInitializer initializer) {
        this.values = initializer.apply();
        this.turnDecider = turnDecider;
    }

    public void move(Position source, Position target) {
        validateMovableState(source, target);
        changePiecePositions(source, target);
        turnDecider.nextState();
    }

    private void validateMovableState(Position source, Position target) {
        validateSourceNotEmpty(source);
        validateCorrectTurn(source);
        validateNotFinished();
    }

    private void validateSourceNotEmpty(Position source) {
        if (isEmptyPiece(pieceAt(source))) {
            throw new IllegalArgumentException(ERROR_SOURCE_PIECE_EMPTY);
        }
    }

    private void validateCorrectTurn(Position source) {
        if (!turnDecider.isTurnOf(pieceAt(source))) {
            throw new IllegalArgumentException(ERROR_NOT_CORRECT_TURN);
        }
    }

    private void validateNotFinished() {
        if (isFinished()) {
            throw new IllegalStateException(ERROR_NOT_MOVABLE_CHESS_FINISHED);
        }
    }

    public boolean isFinished() {
        return turnDecider.isFinished();
    }

    private void changePiecePositions(Position source, Position target) {
        Piece sourcePiece = pieceAt(source);
        Piece targetPiece = pieceAt(target);

        validateChangeable(source, target, moveType(targetPiece));
        finishIfKingCaptured(targetPiece);

        values.put(target, sourcePiece);
        values.put(source, EMPTY_PIECE);
    }

    private void finishIfKingCaptured(Piece targetPiece) {
        if (targetPiece.isKing()) {
            turnDecider.finish();
        }
    }

    private void validateChangeable(Position source, Position target, MoveType moveType) {
        if (!pieceAt(source).isMovable(new Path(source, target), moveType) || isBlocked(source, target)
            || moveType.isFriendly()) {
            throw new IllegalArgumentException(ERROR_NOT_MOVABLE);
        }
    }

    private MoveType moveType(Piece targetPiece) {
        if (isEmptyPiece(targetPiece)) {
            return MoveType.EMPTY;
        }
        if (turnDecider.isTurnOf(targetPiece)) {
            return MoveType.FRIENDLY;
        }
        return MoveType.ENEMY;
    }

    private boolean isBlocked(Position source, Position target) {
        if (pieceAt(source).isKnight()) {
            return false;
        }

        return new Path(source, target).possiblePositions().stream()
            .anyMatch(position -> !isEmptyPiece(pieceAt(position)));
    }

    private boolean isEmptyPiece(Piece piece) {
        return piece.equals(EMPTY_PIECE);
    }

    private Piece pieceAt(Position position) {
        return values.get(position);
    }

    public double calculateScore() {
        return new ScoreCalculator(values, turnDecider).currentPlayerScore();
    }

    public Map<Position, Piece> getValues() {
        return values;
    }
}
