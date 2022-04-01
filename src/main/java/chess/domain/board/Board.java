package chess.domain.board;

import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.PAWN;

import chess.domain.board.strategy.CreateBoardStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.EmptySpace;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final String PIECE_DOES_NOT_EXIST = "해당 위치에 말이 존재하지 않습니다.";
    private static final String CANNOT_MOVE_OPPONENT_PIECE = "상대편 말은 욺직일 수 없습니다.";
    private static final String ANOTHER_PIECE_EXIST_IN_PATH = "다른 말이 경로에 존재해 이동할 수 없습니다.";
    private static final double PAWN_MINUS_SCORE = 0.5;

    private final Map<Position, Piece> board;

    public Board(final CreateBoardStrategy strategy) {
        board = new HashMap<>(strategy.createPieces());
    }

    public Piece move(final Position start, final Position target, final Color currentColor) {
        final Piece movingPiece = getPiece(start);
        final Piece targetPiece = getPiece(target);
        validatePieceExistIn(movingPiece, currentColor);
        validateMoving(start, target);
        board.put(target, movingPiece);
        board.remove(start);
        return targetPiece;
    }

    private void validatePieceExistIn(final Piece movingPiece, final Color color) {
        if (movingPiece.isEmpty()) {
            throw new IllegalArgumentException(PIECE_DOES_NOT_EXIST);
        }
        if (!movingPiece.isSameColor(color)) {
            throw new IllegalArgumentException(CANNOT_MOVE_OPPONENT_PIECE);
        }
    }

    private void validateMoving(final Position start, final Position target) {
        final Piece movingPiece = getPiece(start);
        final Piece targetPiece = getPiece(target);
        validatePath(movingPiece.calculatePathToValidate(start, target, targetPiece));
    }

    private void validatePath(List<Position> pathToValidate) {
        pathToValidate.forEach(this::validateEmpty);
    }

    private void validateEmpty(final Position current) {
        if (!getPiece(current).isEmpty()) {
            throw new IllegalArgumentException(ANOTHER_PIECE_EXIST_IN_PATH);
        }
    }

    private Piece getPiece(final Position position) {
        return board.getOrDefault(position, new EmptySpace());
    }

    public int countPiece(final PieceType pieceType, final Color color) {
        return (int) board.values()
                .stream()
                .filter(piece -> piece.isSamePiece(pieceType) && piece.isSameColor(color))
                .count();
    }

    public int countDeductedPawns(final Color color) {
        return (int) board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSamePiece(PAWN)
                        && entry.getValue().isSameColor(color))
                .filter(this::hasAnotherPawnInSameColumn)
                .count();
    }

    private boolean hasAnotherPawnInSameColumn(final Map.Entry<Position, Piece> entry) {
        Position current = entry.getKey();
        Piece piece = entry.getValue();
        return Arrays.stream(Row.values())
                .map(row -> new Position(current.getColumn(), row))
                .anyMatch(position -> !current.equals(position) && getPiece(position).equals(piece));
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

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
