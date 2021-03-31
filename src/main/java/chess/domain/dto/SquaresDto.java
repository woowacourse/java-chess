package chess.domain.dto;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SquaresDto {
    private final List<SquareDto> squares;

    public SquaresDto(Board board) {
        this.squares = toDto(board);
    }

    private List<SquareDto> toDto(Board board) {
        Map<Position, Piece> eachBoard = board.board();
        List<Position> positions = Position.getAllPositions();

        Map<PositionDto, Piece> entireBoard = new LinkedHashMap<>();
        for (Position position : positions) {
            entireBoard.put(new PositionDto(position), eachBoard.get(position));
        }

        return entireBoard.entrySet()
                .stream()
                .map(entry -> new SquareDto(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    public List<SquareDto> squares() {
        return squares;
    }
}
