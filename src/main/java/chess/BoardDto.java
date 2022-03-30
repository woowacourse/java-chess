package chess;

import java.util.List;

public class BoardDto {

    private final List<List<String>> dto;

    public BoardDto(List<List<String>> dto) {
        this.dto = dto;
    }

    public List<List<String>> getDto() {
        return dto;
    }
}
