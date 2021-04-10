package chess.dto;

import java.util.List;

public class RankDto {

    private final List<String> pieces;

    public RankDto(List<String> pieces) {
        this.pieces = pieces;
    }

    public List<String> getPieces() {
        return pieces;
    }
}
