package chess.domain.piece;

import chess.domain.Board;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Position> positions;

    public Path(List<Position> positions) {
        this.positions = positions;
    }

    public boolean isAble(Position position) {
        return positions.contains(position);
    }

    public List<Position> removeObstacleInPath(Board board) {
        List<Position> cleanPath = new ArrayList<>();
        for(Position position : positions) {
            if(!board.isEmpty(position)){
                break;
            }
            cleanPath.add(position);
        }
        return cleanPath;
    }

    public List<Position> positions() {
        return positions;
    }
}
