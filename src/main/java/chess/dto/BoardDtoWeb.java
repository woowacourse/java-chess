package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.SquareState;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDtoWeb {

    private final List<PieceDto> board;

    public BoardDtoWeb(Board board) {
        Map<Point, SquareState> squares = board.squares();

        this.board = squares.keySet().stream()
            .map(point -> pieceDtoByPoint(point, squares))
            .collect(Collectors.toList());
    }

    private PieceDto pieceDtoByPoint(Point point, Map<Point, SquareState> squares) {
        return new PieceDto(point, squares.get(point).team(), squares.get(point).piece());
    }

    public List<PieceDto> getBoard() {
        return board;
    }
}
