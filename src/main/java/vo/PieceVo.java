package vo;

public class PieceVo {
    private final int gameId;
    private final String name;
    private final int row;
    private final String col;

    public PieceVo(int gameId, String name, int row, String col) {
        this.gameId = gameId;
        this.name = name;
        this.row = row;
        this.col = col;
    }

    public int getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public String getCol() {
        return col;
    }
}
