package chess.domain.board;

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

    private final Map<Position, Piece> pieces;

    public Board(final CreateBoardStrategy strategy) {
        pieces = new HashMap<>(strategy.createPieces());
    }

    public Piece move(final Position start, final Position target, final Color currentColor) {
        final Piece movingPiece = get(start);
        final Piece targetPiece = get(target);
        validatePieceExistIn(movingPiece, currentColor);
        validateMoving(start, target);
        pieces.put(target, movingPiece);
        pieces.remove(start);
        return targetPiece;
    }

    private void validateMoving(Position start, Position target) {
        final Piece movingPiece = get(start);
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
        final Piece movingPiece = get(start);
        validatePath(movingPiece, start, target);

        final Piece targetPiece = get(target);
        validateTarget(movingPiece, targetPiece);
    }

    private void validateKnight(final Position start, final Position target) {
        final Piece movingPiece = get(start);
        final Piece targetPiece = get(target);
        validateTarget(movingPiece, targetPiece);
    }

    private void validatePawn(final Position start, final Position target) {
        final Piece movingPiece = get(start);
        final Piece targetPiece = get(target);
        final Direction direction = movingPiece.findValidDirection(start, target);
        if (direction.isDiagonal()) {
            validatePawnDiagonalMove(movingPiece, targetPiece);
            return;
        }
        validatePath(movingPiece, start, target);
        if (!targetPiece.equals(new EmptySpace())) {
            throw new IllegalArgumentException(HAS_ANOTHER_PIECE_ERROR);
        }
    }

    private void validatePawnDiagonalMove(Piece movingPiece, Piece targetPiece) {
        if (targetPiece.equals(new EmptySpace()) || targetPiece.getColor() == movingPiece.getColor()) {
            throw new IllegalArgumentException(PAWN_CANNOT_MOVE_DIAGONAL);
        }
    }

    private void validatePieceExistIn(final Piece movingPiece, final Color color) {
        if (movingPiece.equals(new EmptySpace())) {
            throw new IllegalArgumentException(PIECE_DOES_NOT_EXIST);
        }
        if (movingPiece.getColor() != color) {
            throw new IllegalArgumentException(CANNOT_MOVE_OPPONENT_PIECE);
        }
    }

    private void validatePath(final Piece movingPiece, final Position start, final Position target) {
        final Direction direction = movingPiece.findValidDirection(start, target);
        Position current = start.move(direction);
        while (!current.equals(target)) {
            if (!get(current).equals(new EmptySpace())) {
                throw new IllegalArgumentException(ANOTHER_PIECE_EXIST_IN_PATH);
            }
            current = current.move(direction);
        }
    }

    private void validateTarget(final Piece movingPiece, final Piece targetPiece) {
        if (!targetPiece.equals(new EmptySpace()) && movingPiece.getColor() == targetPiece.getColor()) {
            throw new IllegalArgumentException(ANOTHER_SAME_COLOR_PIECE_EXIST);
        }
    }

    private Piece get(final Position position) {
        return pieces.getOrDefault(position, new EmptySpace());
    }

    public double countPiece(PieceType pieceType, Color color) {
        return (double) pieces.values()
                .stream()
                .filter(piece -> piece.isSamePiece(pieceType) && piece.isSameColor(color))
                .count();
    }

    public int countDeductedPawns(Color color) {
        return (int) pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSamePiece(PAWN)
                        && entry.getValue().isSameColor(color))
                .filter(this::hasAnotherPawnInSameColumn)
                .count();
    }

    private boolean hasAnotherPawnInSameColumn(Map.Entry<Position, Piece> piece) {
        return Arrays.stream(Row.values())
                .map(row -> new Position(piece.getKey().getColumn(), row))
                .anyMatch(position -> !piece.getKey().equals(position)
                        && get(position).equals(piece.getValue()));
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
