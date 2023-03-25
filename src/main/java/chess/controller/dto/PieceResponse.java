package chess.controller.dto;

public class PieceResponse {

    private final int fileIndex;
    private final int rankIndex;
    private final Character letter;

    public PieceResponse(int fileIndex, int rankIndex, Character letter) {
        this.fileIndex = fileIndex;
        this.rankIndex = rankIndex;
        this.letter = letter;
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
}
