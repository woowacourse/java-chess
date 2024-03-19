package chess.view.dto;

import java.util.List;

public class RanksDto {

    private final List<String> rank;

    public RanksDto(final List<String> rank) {
        this.rank = rank;
    }

    public List<String> getRank() {
        return rank;
    }
}
