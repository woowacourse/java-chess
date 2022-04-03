package domain.piece;

import domain.Player;
import domain.direction.Direction;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class MovableRangePiece extends Piece {

    public MovableRangePiece(Player player, PieceSymbol pieceSymbol) {
        super(player, pieceSymbol);
    }

    @Override
    protected Direction direction(Position source, Position target) {
        int file = target.getFile() - source.getFile();
        int rank = target.getRank() - source.getRank();
        Direction direction = calculateDirection(file, rank);
        if (!getDirections().contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 해당 기물이 이동할 수 없는 위치입니다.");
        }
        return direction;
    }

    private Direction calculateDirection(int file, int rank) {
        if (Math.abs(file) == Math.abs(rank)) {
            return createDiagonalDirection(file, rank);
        }
        return createStraightDirection(file, rank);
    }

    private Direction createDiagonalDirection(int file, int rank) {
        file = file / Math.abs(file);
        rank = rank / Math.abs(rank);
        return Direction.valueOf(file, rank);
    }

    private Direction createStraightDirection(int file, int rank) {
        if (file == 0) {
            rank = rank / Math.abs(rank);
        }
        if (rank == 0) {
            file = file / Math.abs(file);
        }
        return Direction.valueOf(file, rank);
    }
}
