package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class SpecificLocationPiece extends Piece {

    public SpecificLocationPiece(Player player, PieceSymbol pieceSymbol,
        DirectionsGenerator directionsGenerator) {
        super(player, pieceSymbol, directionsGenerator);
    }

    @Override
    List<Position> calculateAvailablePosition(Position source, Direction direction) {
        List<Position> positions = new ArrayList<>();
        int file = source.getFile() + direction.getFile();
        int rank = source.getRank() + direction.getRank();

        if (checkOverRange(rank, file)) {
            positions.add(Position.of(File.of(file), Rank.of(rank)));
        }
        return positions;
    }
}
