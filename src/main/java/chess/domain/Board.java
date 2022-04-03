package chess.domain;

import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Board {

    private static final int INITIAL_CAPACITY = 64;
    private static final int START_EMPTY_ROW = 6;
    private static final int END_EMPTY_ROW = 3;

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new LinkedHashMap<>(INITIAL_CAPACITY);
        initialPieces();
    }

    public void initialPieces() {
        putPiecesWithoutPawn(board, Row.EIGHT, Color.BLACK);
        putPiecesOnRow(board, Row.SEVEN, new PawnPiece(Color.BLACK));

        for (int i = START_EMPTY_ROW; i >= END_EMPTY_ROW; i--) {
            putPiecesOnRow(board, Row.of(i), new EmptyPiece());
        }

        putPiecesOnRow(board, Row.TWO, new PawnPiece(Color.WHITE));
        putPiecesWithoutPawn(board, Row.ONE, Color.WHITE);
    }

    private void putPiecesWithoutPawn(final Map<Position, Piece> board, final Row row, final Color color) {
        board.put(new Position(Column.A, row), new RookPiece(color));
        board.put(new Position(Column.B, row), new KnightPiece(color));
        board.put(new Position(Column.C, row), new BishopPiece(color));
        board.put(new Position(Column.D, row), new QueenPiece(color));
        board.put(new Position(Column.E, row), new KingPiece(color));
        board.put(new Position(Column.F, row), new BishopPiece(color));
        board.put(new Position(Column.G, row), new KnightPiece(color));
        board.put(new Position(Column.H, row), new RookPiece(color));
    }

    private void putPiecesOnRow(final Map<Position, Piece> board, final Row row, final Piece piece) {
        for (Column column : Column.values()) {
            board.put(new Position(column, row), piece);
        }
    }

    public void isMovable(final Position from, final Position to, final Color turn) {
        final Piece source = board.get(from);
        final Piece target = board.get(to);

        checkSamePosition(from, to);
        checkEmptySource(source);
        checkTurn(source, turn);
        checkMovement(from, to, source, target);
        checkTargetColor(target, turn);
        checkBlocked(from, to, source);
    }

    private void checkSamePosition(final Position from, final Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("[ERROR] source 위치와 target 위치가 같을 수 없습니다.");
        }
    }

    private void checkEmptySource(final Piece source) {
        if (source.equals(new EmptyPiece())) {
            throw new IllegalStateException("[ERROR] source 위치에 기물이 존재하지 않습니다.");
        }
    }

    private void checkTurn(final Piece source, final Color turn) {
        if (!source.isSameColor(turn)) {
            throw new IllegalStateException("[ERROR] 자신의 기물만 이동시킬 수 있습니다.");
        }
    }

    private void checkMovement(final Position from, final Position to, final Piece source, final Piece target) {
        if (!source.isRightMovement(from, to, target.equals(new EmptyPiece()))) {
            throw new IllegalStateException("[ERROR] 행마법에 맞지 않는 이동입니다.");
        }
    }

    private void checkTargetColor(final Piece target, final Color turn) {
        if (target.isSameColor(turn)) {
            throw new IllegalStateException("[ERROR] 자신의 기물이 있는 곳으로 이동시킬 수 없습니다.");
        }
    }

    private void checkBlocked(final Position from, final Position to, final Piece source) {
        if (!source.isJumpable() && isBlocked(from, to)) {
            throw new IllegalStateException("[ERROR] 이동 경로에 기물이 있어 이동할 수 없습니다.");
        }
    }

    private boolean isBlocked(final Position from, final Position to) {
        final Direction direction = decideDirection(from, to);
        final Position next = from.move(direction);
        if (next.equals(to)) {
            return false;
        }
        if (!board.get(next).isEmpty()) {
            return true;
        }
        return isBlocked(next, to);
    }

    private Direction decideDirection(final Position from, final Position to) {
        final int fileDistance = to.calculateColumnDistance(from);
        final int rowDistance = to.calculateRowDistance(from);

        return Direction.of(fileDistance, rowDistance);
    }

    public boolean isCheckmate(final Position to) {
        return board.get(to).isKing();
    }

    public void move(final Position from, final Position to) {
        board.put(to, board.get(from));
        board.put(from, new EmptyPiece());
    }

    public List<Piece> findPieceNotPawn(final Color color) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .filter(piece -> !piece.isPawn())
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Piece> findPawnOnSameColumn(final Color color, final Column column) {
        return findPieceOnSameColumn(column).stream()
                .filter(piece -> piece.isSameColor(color))
                .filter(Piece::isPawn)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Piece> findPieceOnSameColumn(final Column column) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isSameColumn(column))
                .map(Entry::getValue)
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(board));
    }

    public Map<String, Piece> toMap() {
        return board.entrySet()
                .stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .collect(Collectors.toMap(m -> m.getKey().getName(), Map.Entry::getValue));
    }
}
