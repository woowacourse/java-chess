package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.board.Paths;
import chess.domain.position.Position;
import chess.domain.result.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Piece {

    protected PieceType pieceType;
    protected PieceColor pieceColor;

    protected Piece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public abstract List<Direction> directions();

    public boolean isPawn() {
        return this.pieceType.is(PieceType.PAWN);
    }

    public boolean isKing() {
        return this.pieceType.is(PieceType.KING);
    }

    public boolean isKnight() {
        return this.pieceType.is(PieceType.KNIGHT);
    }

    public boolean isSameSide(Piece that) {
        return !isEnemyOrEmpty(that);
    }

    public boolean isEnemy(Piece that) {
        return this.pieceColor.equals(that.pieceColor.reversed());
    }

    public boolean isEmpty() {
        return this.pieceType.equals(PieceType.EMPTY);
    }

    public boolean isEnemyOrEmpty(Piece that) {
        return isEnemy(that) || that.isEmpty();
    }

    public boolean hasColor(PieceColor color) {
        return this.pieceColor.equals(color);
    }

    public boolean canMoveLimited() {
        return isKing() || isKnight() || isPawn();
    }

    public Paths generatePaths(Position current) {
        return new Paths(directions().stream()
            .map(direction -> findPathInDirection(direction, current))
            .collect(Collectors.toList()));
    }

    private Path findPathInDirection(Direction direction, Position currentPosition) {
        List<Position> positions = new ArrayList<>();
        while (!currentPosition.isBlockedWhenGoTo(direction)) {
            positions.add(currentPosition.moveTo(direction));
            currentPosition = currentPosition.moveTo(direction);
            if (canMoveLimited()) {
                break;
            }
        }
        return new Path(positions);
    }

    public String color() {
        return pieceColor.getColor();
    }

    public Score score() {
        return pieceType.getScore();
    }

    public String getName() {
        if (pieceColor.equals(PieceColor.BLACK)) {
            return this.pieceType.toBlack();
        }
        return this.pieceType.getType();
    }
}
