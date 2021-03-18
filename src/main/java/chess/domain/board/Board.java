package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Vertical;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Line> lines;

    public Board(List<Line> lines){
        this.lines = lines;
    }

    public List<Line> getLines(){
        return new ArrayList<>(lines);
    }

    public Square of(Vertical vertical, Horizontal horizontal){
        final Line horizontalLine = lines.get(vertical.getIndex());
        return horizontalLine.getIndex(horizontal.getIndex());
    }
}
