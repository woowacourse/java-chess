package chess.domain.dto;

public class BoardDTO {

    private final String position;
    private final String fileName;

    public BoardDTO(String position, String fileName) {
        this.position = position;
        this.fileName = fileName;
    }

    public String getPosition() {
        return position;
    }

    public String getFileName() {
        return fileName;
    }
}
