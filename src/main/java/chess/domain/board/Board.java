package chess.domain.board;

import java.util.List;

public class Board {
    private final List<Line> lines;

    public Board(List<Line> lines){
        this.lines = lines;
    }

    public Square of(){
        return lines.get(0).get();
    }
}
