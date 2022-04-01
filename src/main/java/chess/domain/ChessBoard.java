package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
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

    private static final int RUNNING_KING_COUNT = 2;

    private final Map<Position, Piece> pieces;

    public ChessBoard(PiecesGenerator piecesGenerator) {
        this.pieces = piecesGenerator.generate();
        fillEmptyPieceIfAbsent();
    }

    private void fillEmptyPieceIfAbsent() {
        for (Column column : Column.values()) {
            fillEmptyPieceInColumn(column);
        }
    }

    private void fillEmptyPieceInColumn(Column column) {
        for (Row row : Row.values()) {
            this.pieces.computeIfAbsent(Position.of(column, row), value -> EmptyPiece.getInstance());
        }
    }

    public void move(GameCommand gameCommand) {
        Position from = gameCommand.getFromPosition();
        Position to = gameCommand.getToPosition();
        Piece piece = selectPiece(from);
        Map<Direction, List<Position>> movablePositions = piece.getMovablePositions(from);
        List<Position> finalMovablePositions = generateMovablePositionsWithBlock(from, piece, movablePositions);
        checkMovable(to, finalMovablePositions);
        movePiece(from, to, piece);
    }

    private void checkMovable(Position to, List<Position> finalMovablePositions) {
        if (!finalMovablePositions.contains(to)) {
            throw new IllegalArgumentException("해당 말은 입력한 위치로 이동할 수 없습니다.");
        }
    }

    private void movePiece(Position from, Position to, Piece piece) {
        pieces.put(to, piece);
        pieces.put(from, EmptyPiece.getInstance());
    }

    public List<Position> generateMovablePositionsWithBlock(Position nowPosition, Piece piece,
                                                            Map<Direction, List<Position>> movablePositions) {
        List<Position> result = new ArrayList<>();
        for (Map.Entry<Direction, List<Position>> entry : movablePositions.entrySet()) {
            addMovablePositionsWithBlock(piece, result, entry.getValue());
        }

        if (piece.isSamePieceName(PieceName.PAWN)) {
            addDiagonalMoveForPawn(nowPosition, piece, result);
        }
        return Collections.unmodifiableList(result);
    }

    private void addMovablePositionsWithBlock(Piece piece, List<Position> result, List<Position> positions) {
        if (!positions.isEmpty()) {
            int cutIndex = getCutIndex(piece, positions);
            result.addAll(positions.subList(0, cutIndex));
        }
    }

    private int getCutIndex(Piece nowPiece, List<Position> positions) {
        int cutIndex = 0;
        while (isInRangeAndEmptyPosition(positions, cutIndex)) {
            cutIndex++;
        }
        Piece target = selectPiece(positions.get(cutIndex));
        if (isBlockedByTargetPiece(nowPiece, target)) {
            return cutIndex;
        }
        return cutIndex + 1;
    }

    private boolean isInRangeAndEmptyPosition(List<Position> positions, int cutIndex) {
        return cutIndex < positions.size() - 1 && selectPiece(positions.get(cutIndex)).isEmpty();
    }

    private boolean isBlockedByTargetPiece(Piece nowPiece, Piece target) {
        return target.isSameColor(nowPiece) || (nowPiece.isSamePieceName(PieceName.PAWN) && !target.isEmpty());
    }

    private void addDiagonalMoveForPawn(Position nowPosition, Piece piece, List<Position> result) {
        Direction direction = Direction.pawnDirection(piece.getColor());
        List<Direction> diagonalDirections = direction.getDiagonal();
        List<Position> targetPositions = diagonalDirections.stream()
                .map(nowPosition::toDirection)
                .collect(Collectors.toList());
        addDiagonalPositionsIfEnemyForPawn(piece, result, targetPositions);
    }

    private void addDiagonalPositionsIfEnemyForPawn(Piece piece, List<Position> result, List<Position> targetPositions) {
        for (Position position : targetPositions) {
            addDiagonalPositionIfEnemyForPawn(piece, result, position);
        }
    }

    private void addDiagonalPositionIfEnemyForPawn(Piece piece, List<Position> result, Position position) {
        Piece targetPiece = selectPiece(position);
        if (!targetPiece.isSameColor(piece) && !targetPiece.isEmpty()) {
            result.add(position);
        }
    }

    public List<Piece> getPiecesOnColumn(Column column, Color color) {
        List<Piece> result = new ArrayList<>();
        for (Row row : Row.values()) {
            result.add(pieces.get(Position.of(column, row)));
        }
        return result.stream()
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.toList());
    }

    public List<List<Piece>> getPiecesOnColumns(Color color) {
        List<List<Piece>> result = new ArrayList<>();
        for (Column column : Column.values()) {
            result.add(getPiecesOnColumn(column, color));
        }
        return result;
    }

    public boolean isEnd() {
        long kingCount = pieces.values().stream()
                .filter(p -> p.isSamePieceName(PieceName.KING))
                .count();
        return kingCount != RUNNING_KING_COUNT;
    }

    public Color getColor(Position position) {
        return selectPiece(position).getColor();
    }

    public Piece selectPiece(Position position) {
        return pieces.get(position);
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
