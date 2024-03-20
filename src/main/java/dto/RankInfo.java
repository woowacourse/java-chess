package dto;

import java.util.List;
import view.PieceShape;

public class RankInfo{

    private final List<String> piecesOfRank;

    public RankInfo(final List<String> piecesOfRank) {
        this.piecesOfRank = piecesOfRank;
    }

    public List<String> piecesOfRank() {
        return piecesOfRank;
    }
}
