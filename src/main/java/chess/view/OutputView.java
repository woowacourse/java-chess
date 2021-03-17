package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Line;

import java.util.Collections;
import java.util.List;

public class OutputView {

    public static void printInitialBoard(final Board board) {
        List<Line> lines = board.getLines();
        Collections.reverse(lines);
        for(final Line line : lines){
            System.out.println(line);
        }
    }
}
