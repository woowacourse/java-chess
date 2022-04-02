package chess.web.dto;

import static java.util.stream.Collectors.toList;

import chess.domain.piece.Piece;
import java.util.List;

public class BoardResponse {

    private final List<PieceResponse> value;

    public BoardResponse(List<List<Piece>> board) {
        this.value = board.stream()
                .flatMap(List::stream)
                .map(PieceResponse::new)
                .collect(toList());
    }

    public List<PieceResponse> getValue() {
        return value;
    }
}
