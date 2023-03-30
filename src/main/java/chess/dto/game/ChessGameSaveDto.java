package chess.dto.game;

import java.util.List;

public class ChessGameSaveDto {

    private final int size;
    private final List<String> pieceTypes;
    private final List<String> pieceFiles;
    private final List<String> pieceRanks;
    private final List<String> pieceTeams;
    private final List<String> lastTurns;


    public ChessGameSaveDto(final List<String> pieceTypes, final List<String> pieceFiles,
                            final List<String> pieceRanks, final List<String> pieceTeams, final List<String> lastTurns) {
        this.size = pieceTypes.size();
        this.pieceTypes = pieceTypes;
        this.pieceFiles = pieceFiles;
        this.pieceRanks = pieceRanks;
        this.pieceTeams = pieceTeams;
        this.lastTurns = lastTurns;
    }

    public int getSize() {
        return size;
    }

    public List<String> getPieceTypes() {
        return pieceTypes;
    }

    public List<String> getPieceFiles() {
        return pieceFiles;
    }

    public List<String> getPieceRanks() {
        return pieceRanks;
    }

    public List<String> getPieceTeams() {
        return pieceTeams;
    }

    public List<String> getLastTurns() {
        return lastTurns;
    }
}
