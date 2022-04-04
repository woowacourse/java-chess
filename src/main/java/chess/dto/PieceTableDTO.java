package chess.dto;

public class PieceTableDTO {
    private final int lineNumber;
    private final int positionX;
    private final int positionY;
    private final String team;
    private final String type;
    private final boolean firstTurn;
    private final Long gameId;

    public PieceTableDTO(int lineNumber, int positionX, int positionY, String team, String type, boolean firstTurn,
                         Long gameId) {
        this.lineNumber = lineNumber;
        this.positionX = positionX;
        this.positionY = positionY;
        this.team = team;
        this.type = type;
        this.firstTurn = firstTurn;
        this.gameId = gameId;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public String getTeam() {
        return team;
    }

    public String getType() {
        return type;
    }

    public boolean isFirstTurn() {
        return firstTurn;
    }

    public Long getGameId() {
        return gameId;
    }
}
