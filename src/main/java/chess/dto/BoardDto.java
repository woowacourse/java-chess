package chess.dto;

import chess.domain.board.Board;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

    private final List<RowDto> boardDisplay;

    public BoardDto(Board board) {
        this.boardDisplay = board.getRows()
                .stream()
                .map(RowDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<RowDto> getDisplay() {
        return boardDisplay;
    }
}
