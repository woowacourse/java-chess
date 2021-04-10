package chess.controller.dto.board;

import chess.domain.board.position.Path;

import java.util.List;
import java.util.stream.Collectors;

public class MovablePathResponseDto {
    private final List<PositionResponseDto> path;

    public MovablePathResponseDto(final List<PositionResponseDto> path) {
        this.path = path;
    }

    public static MovablePathResponseDto toPath(final Path path) {
        return new MovablePathResponseDto(path.stream()
                .map(position -> new PositionResponseDto(position.getHorizontalIndex(), position.getVerticalIndex()))
                .collect(Collectors.toList()));
    }

    public List<PositionResponseDto> getPath() {
        return path;
    }
}
