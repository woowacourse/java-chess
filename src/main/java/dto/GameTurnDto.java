package dto;

public class GameTurnDto {

    private final String turnOfColor;

    public GameTurnDto(String turnOfColor) {
        this.turnOfColor = turnOfColor;
    }

    public String getTurnOfColor() {
        return turnOfColor;
    }
}
