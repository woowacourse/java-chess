package chess.domain.player;

public class PlayersIds {
    private final Long whitePlayerId;
    private final Long blackPlayerId;

    public PlayersIds(Long whitePlayerId, Long blackPlayerId) {
        this.whitePlayerId = whitePlayerId;
        this.blackPlayerId = blackPlayerId;
    }

    public Long getWhitePlayerId() {
        return whitePlayerId;
    }

    public Long getBlackPlayerId() {
        return blackPlayerId;
    }
}
