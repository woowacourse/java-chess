package chess.piece;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class King extends Piece {
    public King(Team team) {
        super(team, "K");
    }

    @Override
    public List<Position> getMovablePositionsRegardlessOtherPieces(Position position) {
        List<Position> positions = new ArrayList<>();
        for (File file : position.fileValuesWithDifferenceBelow(1)) {
            for (Rank rank : position.rankValuesWithDifferenceBelow(1)) {
                positions.add(Position.of(file, rank));
            }
        }
        positions.remove(position);
        return positions;
    }

    @Override
    public List<Position> findTraceBetween(Position start, Position end) {
        if (start.isNotDistanceOneSquare(end)) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
        return Collections.emptyList();
    }
}
