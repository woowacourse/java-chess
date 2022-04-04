package chess.domain.game;

import chess.domain.piece.Team;

public class WinTeam {

    public static final String WIN_BY_SCORE = "Win By Score";
    public static final String WIN_BY_KING_DEAD = "Win By King Dead";
    private final String value;
    private final String winType;

    public WinTeam(final double blackScore, final double whiteScore, final DeadPieces deadPieces) {
        Team winTeam = Team.NONE;
        String winType = WIN_BY_SCORE;
        if (whiteScore > blackScore) {
            winTeam = Team.WHITE;
        }
        if (whiteScore <= blackScore) {
            winTeam = Team.BLACK;
        }
        if (deadPieces.isKingDead()) {
            winTeam = deadPieces.getTeamOfDeadKing();
            winType = WIN_BY_KING_DEAD;
        }
        this.value = winTeam.getValue();
        this.winType = winType;
    }

    public String getValue() {
        return value;
    }

    public String getWinType() {
        return winType;
    }
}
