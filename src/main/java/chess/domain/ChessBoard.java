package chess.domain;

import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoard {
    private final List<Point> board;

    private ChessBoard(List<Point> board) {
        this.board = board;
    }

    public static ChessBoard getInstance() {
        List<Point> points = createBoard();
        return new ChessBoard(points);
    }

    private static List<Point> createBoard() {
        return Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> (new Point(file, rank))))
                .collect(Collectors.toList());
    }

    public List<Point> getBoard() {
        return board;
    }
}
