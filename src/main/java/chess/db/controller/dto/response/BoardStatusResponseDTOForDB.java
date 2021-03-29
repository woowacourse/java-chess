package chess.db.controller.dto.response;

import java.util.List;

public class BoardStatusResponseDTOForDB {
    private final List<String> cellsStatus;
    private final boolean isKingDead;

    public BoardStatusResponseDTOForDB(List<String> cellsStatus, boolean isKingDead) {
        this.cellsStatus = cellsStatus;
        this.isKingDead = isKingDead;
    }

    public List<String> getCellsStatus() {
        return cellsStatus;
    }

    public boolean isKingDead() {
        return isKingDead;
    }
}
