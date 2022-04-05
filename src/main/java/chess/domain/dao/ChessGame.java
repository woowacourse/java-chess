package chess.domain.dao;

public class ChessGame {

    private String name;
    private boolean isEnd;

    public ChessGame(String name, boolean isEnd) {
        this.name = name;
        this.isEnd = isEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
