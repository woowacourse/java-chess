package chess.domain.board;

import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;

import chess.domain.GameState;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.EmptySpace;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.Map;

public class Board {

    private static final String HAS_ANOTHER_PIECE_ERROR = "다른 말이 존재해 이동할 수 없습니다.";
    private static final String PAWN_CANNOT_MOVE_DIAGONAL = "폰은 상대 말을 공격할 때만 대각선으로 이동할 수 있습니다.";
    private static final String PIECE_DOES_NOT_EXIST = "해당 위치에 말이 존재하지 않습니다.";
    private static final String CANNOT_MOVE_OPPONENT_PIECE = "상대편 말은 욺직일 수 없습니다.";
    private static final String ANOTHER_PIECE_EXIST_IN_PATH = "다른 말이 경로에 존재해 이동할 수 없습니다.";
    private static final String ANOTHER_SAME_COLOR_PIECE_EXIST = "같은 팀의 다른 말이 존재해 이동할 수 없습니다.";
    private static final int NO_KING_EXIST = 0;
    private static final double PAWN_MINUS_SCORE = 0.5;

    private final Map<Position, Piece> pieces;
    private GameState gameState;

    public Board(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
        gameState = GameState.READY;
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

    private void validateMoving(final Position start, final Position target) {
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
        if (!targetPiece.isEmpty()) {
            throw new IllegalArgumentException(HAS_ANOTHER_PIECE_ERROR);
        }
    }

    private void validatePawnDiagonalMove(final Piece movingPiece, final Piece targetPiece) {
        if (targetPiece.isEmpty() || movingPiece.hasSameColor(targetPiece)) {
            throw new IllegalArgumentException(PAWN_CANNOT_MOVE_DIAGONAL);
        }
    }

    private void validatePieceExistIn(final Piece movingPiece, final Color color) {
        if (movingPiece.isEmpty()) {
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
            if (!get(current).isEmpty()) {
                throw new IllegalArgumentException(ANOTHER_PIECE_EXIST_IN_PATH);
            }
            current = current.move(direction);
        }
    }

    private void validateTarget(final Piece movingPiece, final Piece targetPiece) {
        if (!targetPiece.isEmpty() && movingPiece.getColor() == targetPiece.getColor()) {
            throw new IllegalArgumentException(ANOTHER_SAME_COLOR_PIECE_EXIST);
        }
    }

    private Piece get(final Position position) {
//        return pieces.getOrDefault(position, new EmptySpace());
        return pieces.getOrDefault(position, EmptySpace.getInstance());
    }

    public int countPiece(final PieceType pieceType, final Color color) {
        return (int) pieces.values()
                .stream()
                .filter(piece -> piece.isSamePiece(pieceType) && piece.isSameColor(color))
                .count();
    }

    public int countDeductedPawns(final Color color) {
        return (int) pieces.entrySet()
                .stream()
                .filter(entry -> isPawnWith(entry.getValue(), color))
                .filter(this::hasAnotherPawnInSameColumn)
                .count();
    }

    private boolean isPawnWith(final Piece piece, final Color color) {
        return piece.isSamePiece(PAWN) && piece.isSameColor(color);
    }

    private boolean hasAnotherPawnInSameColumn(final Map.Entry<Position, Piece> piece) {
        return Arrays.stream(Row.values())
                .map(row -> new Position(piece.getKey().getColumn(), row))
                .anyMatch(position -> isAnotherPawnInSameColumn(piece, position));
    }

    private boolean isAnotherPawnInSameColumn(final Map.Entry<Position, Piece> piece,
                                              final Position currentPawnPosition) {
        final Position pawnPosition = piece.getKey();
        final Piece pawn = piece.getValue();
        return !pawnPosition.equals(currentPawnPosition) && get(currentPawnPosition).equals(pawn);
    }

    public boolean isEnd(final Color color) {
        return countPiece(PieceType.KING, color) == NO_KING_EXIST;
    }

    public double calculateScore(Board board, Color color) {
        final double score = Arrays.stream(PieceType.values())
                .mapToDouble(piece -> piece.calculateScore(board.countPiece(piece, color)))
                .sum();
        return score - board.countDeductedPawns(color) * PAWN_MINUS_SCORE;
    }

    public boolean isEnd() {
        return gameState == GameState.END;
    }

    public boolean isReady() {
        return gameState == GameState.READY;
    }

    public boolean isRunning() {
        return gameState.isRunning();
    }

    public void startFirstTurn() {
        gameState = GameState.WHITE_RUNNING;
    }

    public void changeTurn() {
        gameState = gameState.getOpposite();
    }

    public void terminateGame() {
        gameState = GameState.END;
    }

    public Color getCurrentColor() {
        if (gameState == GameState.BLACK_RUNNING) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}

