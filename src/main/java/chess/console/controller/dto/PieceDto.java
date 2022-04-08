package chess.console.controller.dto;

public class PieceDto {
    private final String name;
    private final boolean isLastFile;

    public PieceDto(String name, boolean isLastFile) {
        this.name = name;
        this.isLastFile = isLastFile;
    }

    public String getName() {
        return name;
    }

    public boolean isLastFile() {
        return isLastFile;
    }
}
