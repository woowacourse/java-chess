package chess.web.dto;

public class PlayerDto {

    private long id;
    private String name;
    private int win;
    private int draw;
    private int lose;

    public PlayerDto(long id, String name, int win, int draw, int lose) {
        this.id = id;
        this.name = name;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWin() {
        return win;
    }

    public int getDraw() {
        return draw;
    }

    public int getLose() {
        return lose;
    }
}
