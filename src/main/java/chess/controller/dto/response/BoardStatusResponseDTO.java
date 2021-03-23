package chess.controller.dto.response;

import java.util.ArrayList;
import java.util.List;

public class BoardStatusResponseDTO {
    private final List<String> cellsStatus;

    public BoardStatusResponseDTO(List<String> cellsStatus) {
        this.cellsStatus = new ArrayList<>(cellsStatus);
    }

    public List<String> getCellsStatus() {
        return cellsStatus;
    }
}
