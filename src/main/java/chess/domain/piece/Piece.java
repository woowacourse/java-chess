package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.condition.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Piece {

    private Long id;

    private final Color color;

    private final Shape shape;
    private final List<MoveCondition> moveConditions;
    private Position position;

    public Piece(Color color, Shape shape, Position position, List<MoveCondition> moveConditions) {
        this.color = color;
        this.shape = shape;
        this.position = position;
        this.moveConditions = moveConditions;
    }

    public static Piece createPawn(Color color, int row, int col) {
        if (color == Color.BLACK) {
            return new Piece(Color.BLACK, Shape.PAWN, new Position(row, col), Arrays.asList(new FirstTurnBlackPawnMoveCondition(),
                    new NormalBlackPawnMoveCondition(), new CatchingPieceBlackPawnMoveCondition()));
        }

        return new Piece(Color.WHITE, Shape.PAWN, new Position(row, col), Arrays.asList(new FirstTurnWhitePawnMoveCondition(),
                new NormalWhitePawnMoveCondition(), new CatchingPieceWhitePawnMoveCondition()));
    }

    public static Piece createKing(Color color, int row, int col) {
        return new Piece(color, Shape.KING, new Position(row, col), Collections.singletonList(new KingMoveCondition()));
    }

    public static Piece createQueen(Color color, int row, int col) {
        return new Piece(color, Shape.QUEEN, new Position(row, col), Collections.singletonList(new QueenMoveCondition()));
    }

    public static Piece createRook(Color color, int row, int col) {
        return new Piece(color, Shape.ROOK, new Position(row, col), Collections.singletonList(new RookMoveCondition()));
    }

    public static Piece createKnight(Color color, int row, int col) {
        return new Piece(color, Shape.KNIGHT, new Position(row, col), Collections.singletonList(new KnightMoveCondition()));
    }

    public static Piece createBishop(Color color, int row, int col) {
        return new Piece(color, Shape.BISHOP, new Position(row, col), Collections.singletonList(new BishopMoveCondition()));
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isKing() {
        return this.shape == Shape.KING;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNotation() {
        return shape.getNotation(color);
    }

    public int getColumn() {
        return position.getColumn();
    }

    public int getRow() {
        return position.getRow();
    }

    public boolean isSamePosition(final Position position) {
        return this.position.equals(position);
    }

    public void move(final Position target, final Board board) {
        for (final MoveCondition moveCondition : moveConditions) {
            if (moveCondition.isSatisfyBy(board, this, target)) {
                position = target;
                return;
            }
        }

        throw new IllegalArgumentException("해당 위치로는 이동할 수 없습니다.");
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return shape.getScore();
    }

    public Position getPosition() {
        return position;
    }

    public boolean isPawn() {
        return shape == Shape.PAWN;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;

        final Piece piece = (Piece) o;

        if (color != piece.color) return false;
        if (shape != piece.shape) return false;
        return Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (shape != null ? shape.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    public double calculateGradient(final Position target) {
        return position.calculateGradient(target);
    }

    public Long getId() {
        return id;
    }

    public Shape getShape() {
        return shape;
    }

    public void setPosition(final Position target) {
        this.position = target;
    }

}
