package chess.model.dto;

import chess.model.domain.board.BoardSquare;
import java.util.Set;
import java.util.stream.Collectors;

public class PathDto {

    private Set<String> path;

    public PathDto(Set<BoardSquare> pathSquares) {
        path = pathSquares.stream()
            .map(BoardSquare::getName)
            .collect(Collectors.toSet());
    }

    public Set<String> getPath() {
        return path;
    }
}
