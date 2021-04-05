package chess.controller.dto;

import chess.domain.board.position.Path;

import java.util.List;
import java.util.stream.Collectors;

public class PathResponseDto {

    private final List<String> path;

    public PathResponseDto(List<String> path) {
        this.path = path;
    }

    public static PathResponseDto toPath(Path path) {
        return new PathResponseDto(path.stream()
                .map(position -> position.getHorizontal().name().toLowerCase() + position.getVerticalIndex())
                .collect(Collectors.toList()));
    }

    public List<String> getPath() {
        return path;
    }
}
