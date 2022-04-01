package chess.web.dto;

import static java.util.stream.Collectors.toList;

import chess.domain.Board;
import java.util.List;

public class BoardResponse {

    private final List<PieceResponse> value;

    public BoardResponse(Board board) {
        this.value = board.getValue()
                .stream()
                .flatMap(List::stream)
                .map(PieceResponse::new)
                .collect(toList());
    }

    public List<PieceResponse> getValue() {
        return value;
    }
}
