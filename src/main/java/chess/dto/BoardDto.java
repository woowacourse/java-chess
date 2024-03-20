package chess.dto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import chess.model.Board;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    private final List<LineDto> lines;

    public BoardDto(List<LineDto> lines) {
        this.lines = lines;
    }

    public static BoardDto from(Board board) {
        List<LineDto> lines = new ArrayList<>();
        for (int i = 0; i <= 7; i++) {
            lines.add(LineDto.of(board, i));
        }
        return new BoardDto(lines);
    }

    @Override
    public String toString() {
        return lines.stream()
                .map(LineDto::toString)
                .collect(collectingAndThen(toList(), list -> String.join("\n", list)));
    }
}
