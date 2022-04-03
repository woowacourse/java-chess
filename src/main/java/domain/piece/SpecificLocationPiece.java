package domain.piece;

import domain.Player;
import domain.direction.Direction;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class SpecificLocationPiece extends Piece {

    public SpecificLocationPiece(final Player player, final PieceSymbol pieceSymbol) {
        super(player, pieceSymbol);
    }

    @Override
    protected Direction direction(Position source, Position target) {
        int file = target.getFile() - source.getFile();
        int rank = target.getRank() - source.getRank();
        Direction direction = Direction.valueOf(file, rank);
        if (!getDirections().contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 해당 기물이 이동할 수 없는 위치입니다.");
        }
        return direction;
    }
}
