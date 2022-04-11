package chess.dto;

public class BoardDto {

    private final String symbol;
    private final String team;
    private final String position;
    
    public BoardDto(String symbol, String team, String position) {
        this.symbol = symbol;
        this.team = team;
        this.position = position;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTeam() {
        return team;
    }

    public String getPosition() {
        return position;
    }
}
