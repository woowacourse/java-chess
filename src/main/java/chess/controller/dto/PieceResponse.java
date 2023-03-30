package chess.controller.dto;

public class PieceResponse {

    private final int fileIndex;
    private final int rankIndex;
    private final Character letter;
    private final boolean isWhite;

    public PieceResponse(int fileIndex, int rankIndex, Character letter, boolean isWhite) {
        this.fileIndex = fileIndex;
        this.rankIndex = rankIndex;
        this.letter = letter;
        this.isWhite = isWhite;
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public int getRankIndex() {
        return rankIndex;
    }

    public Character getLetter() {
        return letter;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
