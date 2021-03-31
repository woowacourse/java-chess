package chess.controller.dto;

import chess.domain.board.position.Path;

import java.util.List;
import java.util.stream.Collectors;

public class ShowPathResponseDto {
    List<PositionResponseDto> path;

    public ShowPathResponseDto(final List<PositionResponseDto> path) {
        this.path = path;
    }

    public static ShowPathResponseDto toPath(final Path path) {
        return new ShowPathResponseDto(path.stream()
                .map(position -> new PositionResponseDto(position.getHorizontalIndex(), position.getVerticalIndex()))
                .collect(Collectors.toList()));
    }

    public List<PositionResponseDto> getPath() {
        return path;
    }
}
