package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class MovableRangePiece extends Piece {

    public MovableRangePiece(Player player, PieceSymbol pieceSymbol,
        DirectionsGenerator directionsGenerator) {
        super(player, pieceSymbol, directionsGenerator);
    }

    @Override
    List<Position> calculateAvailablePosition(final Position source, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        int rank = source.getRank() + direction.getRank();
        int file = source.getFile() + direction.getFile();

        while (checkOverRange(rank, file)) {
            positions.add(Position.of( File.of(file),Rank.of(rank)));
            rank += direction.getRank();
            file += direction.getFile();
        }
        return positions;
    }
}
