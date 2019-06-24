package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.MovingRange;
import chess.domain.board.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private PieceColor color;
    private PieceType type;

    public Piece(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;

    }

    public List<Tile> pathOf(Tile current, Tile target, boolean isTargetEmpty) {
        Direction direction = getRange(isTargetEmpty, current)
                .getProperDirection(target.getWidthDiff(current), target.getHeightDiff(current));

        List<Tile> possiblePath = new ArrayList<>();
        Tile nextTile = direction.nextTile(current);
        while (!nextTile.equals(target)) {
            possiblePath.add(nextTile);
            nextTile = direction.nextTile(nextTile);
        }

        return possiblePath;
    }

    public boolean isSameColorWith(Piece piece) {
        return piece.color == color;
    }

    public boolean isType(PieceType type){
        return type == this.type;
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    protected abstract MovingRange getRange(boolean haveTarget, Tile current);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
