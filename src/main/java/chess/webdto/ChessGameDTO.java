package chess.webdto;

import java.util.Map;

public class ChessGameDTO {
    private final Map<String, Map<String, String>> piecePositionByTeam;
    private final String currentTurnTeam;
    private final double whiteTeamScore;
    private final double blackTeamScore;
    private final boolean isPlaying;

    public ChessGameDTO(final Map<String, Map<String, String>> piecePositionByTeam, final String currentTurnTeam,
                        final double whiteTeamScore, final double blackTeamScore, final boolean isPlaying) {
        this.piecePositionByTeam = piecePositionByTeam;
        this.whiteTeamScore = whiteTeamScore;
        this.blackTeamScore = blackTeamScore;
        this.currentTurnTeam = currentTurnTeam;
        this.isPlaying = isPlaying;
    }

    public Map<String, Map<String, String>> getPiecePositionByTeam() {
        return piecePositionByTeam;
    }

    public String getCurrentTurnTeam() {
        return currentTurnTeam;
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore;
    }

    public double getBlackTeamScore() {
        return blackTeamScore;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }
}
