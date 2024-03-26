package view.dto;

import java.util.Collections;
import java.util.List;

public class RankInfo {

    private final List<String> piecesOfRank;

    public RankInfo(final List<String> piecesOfRank) {
        this.piecesOfRank = piecesOfRank;
    }

    public List<String> getPieces() {
        return Collections.unmodifiableList(piecesOfRank);
    }
}
