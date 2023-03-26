package chess.dto.game;

import java.util.List;

public final class ChessGameLoadDto {

    private final List<String> pieceTypes;
    private final List<String> pieceFiles;
    private final List<String> pieceRanks;
    private final String lastTurn;
    private final List<String> pieceTeams;

    public ChessGameLoadDto(final List<String> pieceTypes, final List<String> pieceFiles,
                            final List<String> pieceRanks, final List<String> pieceTeams, final String lastTurn) {
        this.pieceTypes = pieceTypes;
        this.pieceFiles = pieceFiles;
        this.pieceRanks = pieceRanks;
        this.pieceTeams = pieceTeams;
        this.lastTurn = lastTurn;
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

    public String getLastTurn() {
        return lastTurn;
    }
}
