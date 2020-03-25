package chess.domain.chesspieces;

public abstract class ChessPiece {
    private String name;

    public ChessPiece(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
