package chess.domain.board.service.dto;

import java.util.List;

public class AllBoardSearchResponse {

    private final List<Long> ids;

    public AllBoardSearchResponse(final List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> ids() {
        return ids;
    }
}
