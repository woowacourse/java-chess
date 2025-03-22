package view.support;

import chess.board.Position;
import java.util.Comparator;

public class BoardComparator implements Comparator<Position> {

    @Override
    public int compare(Position o1, Position o2) {
        if (o1.row().ordinal() == o2.row().ordinal()) {
            return Integer.compare(o1.column().ordinal(), o2.column().ordinal());
        }
        return Integer.compare(o1.row().ordinal(), o2.row().ordinal());
    }
}
