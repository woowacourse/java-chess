package chess.controller.dto;

public class MovablePathRequestDto {

    String source;

    public MovablePathRequestDto(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
