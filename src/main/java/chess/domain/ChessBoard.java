package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import chess.domain.piece.generator.PiecesGenerator;
import chess.domain.position.Column;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private static final int KING_COUNTS = 2;

    private final Map<Position, Piece> pieces;

    public ChessBoard(final PiecesGenerator piecesGenerator) {
        this.pieces = piecesGenerator.generate();
        fillEmptyPieceIfAbsent();
    }

    private void fillEmptyPieceIfAbsent() {
        for (final Column column : Column.values()) {
            fillEmptyPieceInColumn(column);
        }
    }

    private void fillEmptyPieceInColumn(final Column column) {
        for (final Row row : Row.values()) {
            this.pieces.computeIfAbsent(Position.of(column, row), value -> EmptyPiece.getInstance());
        }
    }

    public void move(final Position from, final Position to) {
        final Piece piece = selectPiece(from);
        final List<Position> finalMovablePositions = getMovablePositions(from, piece);
        System.out.println(finalMovablePositions);
        checkMovable(to, finalMovablePositions);
        movePiece(from, to, piece);
    }

    private List<Position> getMovablePositions(final Position from, final Piece piece) {
        final Map<Direction, List<Position>> movablePositions = piece.getMovablePositions(from);

        return generateMovablePositionsExceptObstacles(from, piece, movablePositions);
    }

    public List<Position> generateMovablePositionsExceptObstacles(final Position position, final Piece piece,
                                                                  final Map<Direction, List<Position>> movablePositions) {
        final List<Position> result = new ArrayList<>();

        for (final Direction direction : movablePositions.keySet()) {
            final List<Position> positions = movablePositions.get(direction);
            addMovablePositionsExceptObstacles(piece, result, positions);
        }

        addDiagonalMoveForPawn(position, piece, result);
        return Collections.unmodifiableList(result);
    }

    private void addMovablePositionsExceptObstacles(final Piece piece, final List<Position> result,
                                                    final List<Position> positions) {
        if (positions.size() != 0) {
            final int removeIndex = getRemoveIndex(piece, positions);
            final List<Position> movablePositions = positions.subList(0, removeIndex);
            result.addAll(movablePositions);
        }
    }

    private int getRemoveIndex(Piece nowPiece, List<Position> positions) {
        int cutIndex = 0;

        while (cutIndex < positions.size() - 1 && selectPiece(positions.get(cutIndex)).isEmpty()) {
            cutIndex++;
        }

        final Piece target = selectPiece(positions.get(cutIndex));
        if (target.isSameColor(nowPiece) || (nowPiece.isSameSymbol(Symbol.PAWN) && !target.isEmpty())) {
            return cutIndex;
        }
        return cutIndex + 1;
    }

    private void checkMovable(final Position to, final List<Position> finalMovablePositions) {
        if (!finalMovablePositions.contains(to)) {
            throw new IllegalArgumentException("해당 말은 입력한 위치로 이동할 수 없습니다.");
        }
    }

    private void movePiece(final Position from, final Position to, final Piece piece) {
        pieces.put(to, piece);
        pieces.put(from, EmptyPiece.getInstance());
    }

    private void addDiagonalMoveForPawn(final Position position, final Piece piece, final List<Position> result) {
        if (piece.isSameSymbol(Symbol.PAWN)) {
            final Direction direction = piece.getPawnDirection();
            final List<Direction> diagonalDirections = direction.getDiagonal();
            final List<Position> diagonalPositions = diagonalDirections.stream()
                    .map(position::toDirection)
                    .collect(Collectors.toList());

            addPositionsIfEnemy(piece, result, diagonalPositions);
        }
    }

    private void addPositionsIfEnemy(final Piece piece, final List<Position> result,
                                     final List<Position> diagonalPositions) {
        for (final Position position : diagonalPositions) {
            addPositionIfEnemy(piece, result, position);
        }
    }

    private void addPositionIfEnemy(final Piece piece, final List<Position> result, final Position position) {
        final Piece targetPiece = selectPiece(position);
        if (!targetPiece.isSameColor(piece) && !targetPiece.isEmpty()) {
            result.add(position);
        }
    }

    public Piece selectPiece(final Position position) {
        return pieces.get(position);
    }

    public boolean isEnd() {
        return getKingCount() != KING_COUNTS;
    }

    private long getKingCount() {
        final long kingCount = pieces.values().stream()
                .filter(piece -> piece.isSameSymbol(Symbol.KING))
                .count();
        return kingCount;
    }

    public Color getWinner() {
        return pieces.values().stream()
                .filter(piece -> piece.isSameSymbol(Symbol.KING))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("킹이 보드에 존재하지 않습니다."))
                .getColor();
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
