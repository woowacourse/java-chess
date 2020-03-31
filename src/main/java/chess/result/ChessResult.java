package chess.result;


public class ChessResult {
    private final Result result;
    private final String name;

    public ChessResult(Result result, String name) {
        this.result = result;
        this.name = name;
    }

    public boolean isDraw() {
        return result.isDraw();
    }

    public String getName() {
        return name;
    }
}
