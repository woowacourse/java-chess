package chess.domain.board;

import java.util.List;

public class LineDto {
    private final List<String> pieces;

    public LineDto(List<String> pieces) {
        this.pieces = pieces;
    }

    public List<String> getPieces() {
        return pieces;
    }
}
