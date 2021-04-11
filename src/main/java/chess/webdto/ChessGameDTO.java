package chess.webdto;

import java.util.Map;

public class ChessGameDTO {
    private final Map<String, Map<String, String>> piecePositionByTeam;
    private final String currentTurnTeam;
    private final Map<String, Double> teamScore;
    private final boolean isPlaying;

    public ChessGameDTO(final Map<String, Map<String, String>> piecePositionByTeam, final String currentTurnTeam,
                        final Map<String, Double> teamScore, final boolean isPlaying) {
        this.piecePositionByTeam = piecePositionByTeam;
        this.currentTurnTeam = currentTurnTeam;
        this.teamScore = teamScore;
        this.isPlaying = isPlaying;
    }

    public Map<String, Map<String, String>> getPiecePositionByTeam() {
        return piecePositionByTeam;
    }

    public String getCurrentTurnTeam() {
        return currentTurnTeam;
    }

    public Map<String, Double> getTeamScore() {
        return teamScore;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }
}
