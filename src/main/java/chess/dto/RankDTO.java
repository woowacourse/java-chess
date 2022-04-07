package chess.dto;

import java.util.List;

public class RankDTO {

    private final List<PieceDTO> rank;

    public RankDTO(List<PieceDTO> rank) {
        this.rank = rank;
    }

    public List<PieceDTO> getRank() {
        return rank;
    }
}
