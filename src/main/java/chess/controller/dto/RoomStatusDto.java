package chess.controller.dto;

public class RoomStatusDto {
    private String name;
    private String turn;

    public String getName() {
        return name;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public void setName(String name) {
        this.name = name;
    }
}

