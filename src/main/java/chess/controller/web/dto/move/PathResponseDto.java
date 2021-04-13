package chess.controller.web.dto.move;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;

import java.util.List;
import java.util.stream.Collectors;

public class PathResponseDto {

    private final List<String> path;

    public PathResponseDto(final List<String> path) {
        this.path = path;
    }

    public static PathResponseDto from(final Path path) {
        return new PathResponseDto(path.stream()
                .map(Position::parseString)
                .collect(Collectors.toList()));
    }

    public List<String> getPath() {
        return path;
    }
}
