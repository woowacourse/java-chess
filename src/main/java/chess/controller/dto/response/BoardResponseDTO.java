package chess.controller.dto.response;

import java.util.ArrayList;
import java.util.List;

public class BoardResponseDTO {
    private final List<String> cellsStatus;

    public BoardResponseDTO(List<String> cellsStatus) {
        this.cellsStatus = new ArrayList<>(cellsStatus);
    }

    public List<String> getCellsStatus() {
        return cellsStatus;
    }
}
