package chess.dto;

public class StateDto {
    private String state;
    private String color;

    public StateDto(String state, String color) {
        this.state = state;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean isWaiting() {
        return state.equals("Waiting");
    }

    public boolean isFinish() {
        return state.equals("End");
    }
}
