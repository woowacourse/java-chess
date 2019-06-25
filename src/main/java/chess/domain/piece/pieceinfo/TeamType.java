package chess.domain.piece.pieceinfo;

public enum TeamType {
    WHITE,
    BLACK,
    NEUTRAL;

    public static TeamType switchTeamType(TeamType currentTeamType) {
        return (currentTeamType == WHITE) ? BLACK : WHITE;
    }
}
