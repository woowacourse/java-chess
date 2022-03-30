package chess.domain.board;

import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;

import chess.domain.board.strategy.CreateBoardStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.EmptySpace;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final String HAS_ANOTHER_PIECE_ERROR = "다른 말이 존재해 이동할 수 없습니다.";
    private static final String PAWN_CANNOT_MOVE_DIAGONAL = "폰은 상대 말을 공격할 때만 대각선으로 이동할 수 있습니다.";
    private static final String PIECE_DOES_NOT_EXIST = "해당 위치에 말이 존재하지 않습니다.";
    private static final String CANNOT_MOVE_OPPONENT_PIECE = "상대편 말은 욺직일 수 없습니다.";
    private static final String ANOTHER_PIECE_EXIST_IN_PATH = "다른 말이 경로에 존재해 이동할 수 없습니다.";
    private static final String ANOTHER_SAME_COLOR_PIECE_EXIST = "같은 팀의 다른 말이 존재해 이동할 수 없습니다.";
    private static final double PAWN_MINUS_SCORE = 0.5;

    private final Map<Position, Piece> pieces;

    public Board(final CreateBoardStrategy strategy) {
        pieces = new HashMap<>(strategy.createPieces());
    }

    public Piece move(final Position start, final Position target, final Color currentColor) {
        final Piece movingPiece = getPiece(start);
        final Piece targetPiece = getPiece(target);
        validatePieceExistIn(movingPiece, currentColor);
        validateMoving(start, target);
        pieces.put(target, movingPiece);
        pieces.remove(start);
        return targetPiece;
    }

    private void validateMoving(final Position start, final Position target) {
        final Piece movingPiece = getPiece(start);
        if (movingPiece.isSamePiece(KNIGHT)) {
            validateKnight(start, target);
            return;
        }
        if (movingPiece.isSamePiece(PAWN)) {
            validatePawn(start, target);
            return;
        }
        validateCommonPiece(start, target);
    }

    private void validateCommonPiece(final Position start, final Position target) {
        final Piece movingPiece = getPiece(start);
        validatePath(movingPiece, start, target);

        final Piece targetPiece = getPiece(target);
        validateTarget(movingPiece, targetPiece);
    }

    private void validateKnight(final Position start, final Position target) {
        final Piece movingPiece = getPiece(start);
        final Piece targetPiece = getPiece(target);
        validateTarget(movingPiece, targetPiece);
    }

    private void validatePawn(final Position start, final Position target) {
        final Piece movingPiece = getPiece(start);
        final Piece targetPiece = getPiece(target);
        final Direction direction = movingPiece.findValidDirection(start, target);
        if (direction.isDiagonal()) {
            validatePawnDiagonalMove(movingPiece, targetPiece);
            return;
        }
        validatePath(movingPiece, start, target);
        if (!targetPiece.isEmpty()) {
            throw new IllegalArgumentException(HAS_ANOTHER_PIECE_ERROR);
        }
    }

    private void validatePawnDiagonalMove(final Piece movingPiece, final Piece targetPiece) {
        if (targetPiece.isEmpty() || targetPiece.hasSameColor(movingPiece)) {
            throw new IllegalArgumentException(PAWN_CANNOT_MOVE_DIAGONAL);
        }
    }

    private void validatePieceExistIn(final Piece movingPiece, final Color color) {
        if (movingPiece.isEmpty()) {
            throw new IllegalArgumentException(PIECE_DOES_NOT_EXIST);
        }
        if (!movingPiece.isSameColor(color)) {
            throw new IllegalArgumentException(CANNOT_MOVE_OPPONENT_PIECE);
        }
    }

    private void validatePath(final Piece movingPiece, final Position start, final Position target) {
        final Direction direction = movingPiece.findValidDirection(start, target);
        Position current = start.move(direction);
        while (!current.equals(target)) {
            validateEmpty(current);
            current = current.move(direction);
        }
    }

    private void validateEmpty(final Position current) {
        if (!getPiece(current).isEmpty()) {
            throw new IllegalArgumentException(ANOTHER_PIECE_EXIST_IN_PATH);
        }
    }

    private void validateTarget(final Piece movingPiece, final Piece targetPiece) {
        if (!targetPiece.isEmpty() && targetPiece.hasSameColor(movingPiece)) {
            throw new IllegalArgumentException(ANOTHER_SAME_COLOR_PIECE_EXIST);
        }
    }

    private Piece getPiece(final Position position) {
        return pieces.getOrDefault(position, new EmptySpace());
    }

    public double countPiece(final PieceType pieceType, final Color color) {
        return (double) pieces.values()
                .stream()
                .filter(piece -> piece.isSamePiece(pieceType) && piece.isSameColor(color))
                .count();
    }

    public int countDeductedPawns(final Color color) {
        return (int) pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSamePiece(PAWN)
                        && entry.getValue().isSameColor(color))
                .filter(this::hasAnotherPawnInSameColumn)
                .count();
    }

    private boolean hasAnotherPawnInSameColumn(final Map.Entry<Position, Piece> piece) {
        return Arrays.stream(Row.values())
                .map(row -> new Position(piece.getKey().getColumn(), row))
                .anyMatch(position -> !piece.getKey().equals(position)
                        && getPiece(position).equals(piece.getValue()));
    }

    public boolean isKingCaught(Color color) {
        return countPiece(KING, color) == 0;
    }

    public double calculateScore(Color color) {
        double score = Arrays.stream(PieceType.values())
                .mapToDouble(piece -> piece.calculateScore(countPiece(piece, color)))
                .sum();
        return score - countDeductedPawns(color) * PAWN_MINUS_SCORE;
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
