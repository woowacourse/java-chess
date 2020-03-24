package chess;

public class Position {
    private final ChessFile chessFile;
    private final ChessRank chessRank;

    public Position(ChessFile chessFile, ChessRank chessRank) {
        this.chessFile = chessFile;
        this.chessRank = chessRank;
    }

    @Override
    public String toString() {
        return String.format("%s%s", chessFile, chessRank);
    }
}
