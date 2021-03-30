package chess.controller.dto.response;

import java.util.List;

public class BoardStatusResponseDTO {
    private final List<String> cellsStatus;
    private final boolean isKingDead;

    public BoardStatusResponseDTO(List<String> cellsStatus, boolean isKingDead) {
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
