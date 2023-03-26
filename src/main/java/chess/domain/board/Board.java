package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    public static final int LOWER_BOUNDARY = 1;
    public static final int UPPER_BOUNDARY = 8;

    private static final int PAWN_SPECIAL_COUNT = 2;
    private static final double PAWN_SPECIAL_PRICE = 0.5;

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(final Position source, final Position target) {
        final List<Position> movablePosition = findMovablePosition(source);

        if (!movablePosition.contains(target)) {
            throw new IllegalArgumentException("해당 위치로 기물을 움직일 수 없습니다.");
        }

        final Piece sourcePiece = board.get(source);

        if (sourcePiece.isPawn()) {
            sourcePiece.changePawnMoveState();
        }

        board.put(target, sourcePiece);
        board.put(source, new Empty());
    }

    private List<Position> findMovablePosition(final Position position) {
        try {
            final Piece piece = board.get(position);
            return piece.findMovablePosition(position, this);
        } catch (UnsupportedOperationException e) {
            throw new IllegalArgumentException("기물이 있는 위치를 선택해주세요.", e);
        }
    }

    public Side calculateAdvantageSide() {
        final double whiteSidePrice = calculatePriceBySide(Side.WHITE);
        final double blackSidePrice = calculatePriceBySide(Side.BLACK);

        if (whiteSidePrice > blackSidePrice) {
            return Side.WHITE;
        }
        if (whiteSidePrice < blackSidePrice) {
            return Side.BLACK;
        }
        return Side.NEUTRALITY;
    }

    public double calculatePriceBySide(final Side side) {
        return calculateTotalPriceBySide(side) - calculatePawnSpecialPriceBySide(side);
    }

    private double calculateTotalPriceBySide(final Side side) {
        return board.values().stream()
                .filter(piece -> piece.side() == side)
                .mapToDouble(Piece::price)
                .sum();
    }

    private double calculatePawnSpecialPriceBySide(final Side side) {
        final Map<Integer, Long> fileIndexByPawnCount = getFileIndexByPawnCount(side);

        return fileIndexByPawnCount.values().stream()
                .filter(pawnCount -> pawnCount >= PAWN_SPECIAL_COUNT)
                .mapToDouble(pawnCount -> pawnCount * PAWN_SPECIAL_PRICE)
                .sum();
    }

    private Map<Integer, Long> getFileIndexByPawnCount(final Side side) {
        return board.keySet().stream()
                .filter(position -> board.get(position).isPawn())
                .filter(position -> board.get(position).side() == side)
                .collect(Collectors.groupingBy(Position::fileIndex, Collectors.counting()));
    }

    public boolean isKingCaptured() {
        return isKingCaptured(Side.WHITE) || isKingCaptured(Side.BLACK);
    }

    public boolean isKingCaptured(final Side side) {
        return board.values().stream()
                .filter(piece -> piece.side() == side)
                .noneMatch(piece -> piece.type() == Type.KING);
    }

    public boolean isAllyPosition(final Position position, final Position otherPosition) {
        final Side side = findSideByPosition(position);
        final Side otherSide = findSideByPosition(otherPosition);
        return side.isAlly(otherSide);
    }

    public boolean isEnemyPosition(final Position position, final Position otherPosition) {
        final Side side = findSideByPosition(position);
        final Side otherSide = findSideByPosition(otherPosition);
        return side.isEnemy(otherSide);
    }

    public Side findSideByPosition(final Position position) {
        final Piece piece = board.get(position);
        return piece.side();
    }

    public static boolean isInRange(final int fileIndex, final int rankIndex) {
        return File.isIndexValid(fileIndex) && Rank.isIndexValid(rankIndex);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
