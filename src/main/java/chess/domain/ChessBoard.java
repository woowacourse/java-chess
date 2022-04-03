package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
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
    private static final String NOT_MOVABLE_EXCEPTION_MESSAGE = "해당 Position으로 이동할 수 없습니다.";

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
        Position fromPosition = gameCommand.getFromPosition();
        Position toPosition = gameCommand.getToPosition();
        Piece fromPiece = selectPiece(fromPosition);

        checkEnemyOrEmpty(fromPiece, selectPiece(toPosition));
        checkMovableDirection(fromPosition, toPosition, fromPiece);
        checkBlockInDirection(fromPosition, toPosition, fromPiece);
        movePiece(fromPosition, toPosition, fromPiece);
    }

    private void checkEnemyOrEmpty(Piece fromPiece, Piece toPiece) {
        if (!toPiece.isEmpty() && fromPiece.isSameColor(toPiece)) {
            throw new IllegalStateException(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }
    }

    private void checkMovableDirection(Position fromPosition, Position toPosition, Piece fromPiece) {
        if (!fromPiece.canMove(fromPosition, toPosition)) {
            throw new IllegalStateException(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }
    }

    private void checkBlockInDirection(Position fromPosition, Position toPosition, Piece fromPiece) {
        Direction direction = Direction.getDirectionByPositions(fromPosition, toPosition);
        checkRouteNotBlock(fromPosition, toPosition, direction);
        if (isPawnAndDiagonalDirection(fromPiece, direction)) {
            checkEnemyInDiagonal(fromPiece, selectPiece(toPosition));
        }
    }

    private void checkRouteNotBlock(Position fromPosition, Position toPosition, Direction direction) {
        for (Position nextPosition = fromPosition.toDirection(direction);
             nextPosition != toPosition;
             nextPosition = nextPosition.toDirection(direction)) {
            checkEmptyPiece(nextPosition);
        }
    }

    private void checkEmptyPiece(Position nextPosition) {
        if (!selectPiece(nextPosition).isEmpty()) {
            throw new IllegalStateException(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }
    }

    private boolean isPawnAndDiagonalDirection(Piece fromPiece, Direction direction) {
        return fromPiece.isSamePieceType(PieceType.PAWN) && !direction.isPawnStraigtDirection();
    }

    private void checkEnemyInDiagonal(Piece fromPiece, Piece toPiece) {
        if (toPiece.isEmpty() || fromPiece.isSameColor(toPiece)) {
            throw new IllegalStateException(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }
    }

    private void movePiece(Position from, Position to, Piece piece) {
        pieces.put(to, piece);
        pieces.put(from, EmptyPiece.getInstance());
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
                .filter(p -> p.isSamePieceType(PieceType.KING))
                .count();
        return kingCount != RUNNING_KING_COUNT;
    }

    public Color getPositionColor(Position position) {
        return selectPiece(position).getColor();
    }

    public Piece selectPiece(Position position) {
        return pieces.get(position);
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
