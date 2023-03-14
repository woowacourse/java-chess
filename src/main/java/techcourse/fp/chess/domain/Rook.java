package techcourse.fp.chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(final Side side) {
        super(side);
    }

    @Override
    public List<Position> findMovablePositions(final Position basePosition) {
        List<Position> movablePositions = new ArrayList<>();
        File currentFile = basePosition.getFile();
        Rank currentRank = basePosition.getRank();

        for (File file : File.values()) {
            if (file != currentFile) {
                movablePositions.add(Position.of(file, currentRank));
            }
        }

        for (Rank rank : Rank.values()) {
            if (rank != currentRank) {
                movablePositions.add(Position.of(currentFile, rank));
            }
        }

        return movablePositions;
    }
}
