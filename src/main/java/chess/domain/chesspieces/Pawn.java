package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.moverules.Direction;
import chess.domain.position.Position;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.Arrays;
import java.util.Objects;

public class Pawn extends Piece {
    private static PieceName pieceName = PieceName.valueOf("PAWN");
    private boolean isMoved;

    /*
    움직임 여부를 chess board가 알고 있는데 어떻게 true로 바꿀 수 있을까?

     */
    public Pawn(Player player) {
        super(player, pieceName);
        Direction pawnDirection = Direction.TOP;
        if (player.equals(Player.BLACK)) {
            pawnDirection = Direction.DOWN;
        }
        directions.addAll(Arrays.asList(pawnDirection,
                Direction.DIAGONAL_TOP_LEFT, Direction.DIAGONAL_TOP_RIGHT));
        isMoved = false;
    }

    @Override
    public boolean movable(Position source, Position target){
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        int rowDiff = Row.getDiff(source.getRow(), target.getRow());
        int columnDiff = Column.getDiff(source.getColumn(), target.getColumn());
        if (!hasMoveRule(Direction.getMoveRule(source, target))) {
            return false;
        }
        isMoved = true;
        return validateMovableTileSize(rowDiff, columnDiff);
    }


    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        int availableRowDiff = 1;
        int availableColumnDiff = 2;
        if (isMoved) {
            availableColumnDiff = 1;
        }
        return Math.abs(rowDiff) <= availableRowDiff && Math.abs(columnDiff) <= availableColumnDiff;
    }
}
