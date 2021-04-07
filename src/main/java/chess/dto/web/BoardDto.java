package chess.dto.web;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.SquareState;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDto {

    private final List<PieceDto> board;

    public BoardDto(Board board) {
        Map<Point, SquareState> squares = board.squares();

        this.board = squares.keySet().stream()
            .map(point -> pieceDtoByPoint(point, squares))
            .collect(Collectors.toList());
    }

    private PieceDto pieceDtoByPoint(Point point, Map<Point, SquareState> squares) {
        return new PieceDto(point, squares.get(point).team(), squares.get(point).piece());
    }

    public Board toEntity() {
        Map<Point, SquareState> squares = new HashMap<>();
        board.forEach(pieceDto -> {
            squares.put(pieceDto.toPointEntity(), pieceDto.toSquareStateEntity());
        });
        return new Board(squares);
    }

    public List<PieceDto> getBoard() {
        return board;
    }
}
