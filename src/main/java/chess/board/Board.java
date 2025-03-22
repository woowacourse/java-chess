package chess.board;

import java.util.List;

public class Board {
    private List<Position> positions;

    public static Board generate(){
        return new Board(Generator.board());
    }

    private Board(List<Position> positions) {
        this.positions = positions;
    }

    public List<Position> positions() {
        return positions;
    }
}
