package chess.domain.board;

import java.util.List;

public class Board {
    private final List<Line> lines;

    public Board(List<Line> lines){
        this.lines = lines;
    }

    public Line getLine(int index){
        return lines.get(index);
    }
}
