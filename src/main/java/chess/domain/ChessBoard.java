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
        MoveChecker moveChecker = new MoveChecker();
        Direction direction = Direction.getDirectionByPositions(fromPosition, toPosition);

        checkMovable(fromPosition, toPosition, direction, moveChecker);
        checkRouteNotBlock(fromPosition, toPosition, direction, moveChecker);
        movePiece(fromPosition, toPosition, selectPiece(fromPosition));
    }

    private void checkMovable(Position fromPosition, Position toPosition,
                              Direction direction, MoveChecker moveChecker) {
        Piece fromPiece = selectPiece(fromPosition);
        Piece toPiece = selectPiece(toPosition);

        moveChecker.checkTargetEnemyOrEmpty(fromPiece, toPiece);
        moveChecker.checkMovableDirection(fromPosition, toPosition, fromPiece);
        moveChecker.checkPawnMove(fromPiece, toPiece, direction);
    }

    private void checkRouteNotBlock(Position fromPosition, Position toPosition,
                                    Direction direction, MoveChecker moveChecker) {
        for (Position nextPosition = fromPosition.toDirection(direction);
             nextPosition != toPosition;
             nextPosition = nextPosition.toDirection(direction)) {
            moveChecker.checkEmpty(selectPiece(nextPosition));
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
