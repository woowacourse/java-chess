package chess.game;

import chess.piece.Color;
import chess.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final int DEAD_KING_COUNT = 1;

    private final Map<Position, Piece> value;
    private final Score score;

    public Board(final Map<Position, Piece> value) {
        this.value = new HashMap<>(value);
        score = new Score();
    }

    public void move(final MoveCommand moveCommand, final Color color) {
        final Position from = moveCommand.getFrom();
        final Position to = moveCommand.getTo();
        final Piece piece = value.get(from);
        validateMove(color, from, to, piece);
        movePiece(from, to, piece);
    }

    public Score calculateBoardScore() {
        score.calculate(new HashMap<>(value));
        return score;
    }

    public boolean isKingDead() {
        return countKingPiece() == DEAD_KING_COUNT;
    }

    private void movePiece(final Position from, final Position to, final Piece piece) {
        if (piece.canMove(from, to)) {
            value.put(to, piece);
            value.remove(from);
            return;
        }
        throw new IllegalArgumentException("이동이 불가능 합니다.");
    }

    private int countKingPiece() {
        return (int) value.values().stream()
                .filter(Piece::isKing)
                .count();
    }

    private void validateMove(final Color color, final Position from, final Position to, final Piece piece) {
        validatePieceExist(from);
        validateSameTeam(from, to);
        validatePieceBlock(from, to);
        validateColor(piece, color);
        validatePawnMove(from, to, piece);
    }

    private void validatePieceExist(final Position position) {
        if (!value.containsKey(position)) {
            throw new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다.");
        }
    }

    private void validateSameTeam(final Position from, final Position to) {
        if (value.containsKey(to) && value.get(from).isSameTeam(value.get(to))) {
            throw new IllegalArgumentException("이동할 위치에 같은색의 말이 존재합니다.");
        }
    }

    private void validatePieceBlock(final Position from, final Position to) {
        final Direction direction = Direction.find(from, to);
        Position movedPosition = from.shift(direction);
        while (!movedPosition.equals(to)) {
            validateBlock(movedPosition);
            movedPosition = movedPosition.shift(direction);
        }
    }

    private void validateColor(final Piece piece, final Color color) {
        if (!piece.isEqualColor(color)) {
            throw new IllegalArgumentException(color + "가 둘 차례입니다.");
        }
    }

    private void validatePawnMove(final Position from, final Position to, final Piece piece) {
        if (piece.isPawn()) {
            validatePawnForwardMove(from, to);
        }
    }

    private void validateBlock(final Position position) {
        if (value.containsKey(position)) {
            throw new IllegalArgumentException("말의 이동 위치가 가로막혀 있습니다.");
        }
    }

    private void validatePawnForwardMove(final Position from, final Position to) {
        if (from.isSameColumn(to) && value.containsKey(to)) {
            throw new IllegalArgumentException("폰은 상대말이 있을 때 직진할 수 없습니다.");
        }
        if (!from.isSameColumn(to) && !value.containsKey(to)) {
            throw new IllegalArgumentException("폰은 상대말이 존재하지 않을 때 대각선으로 이동할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Board{" +
                "value=" + value +
                '}';
    }
}
