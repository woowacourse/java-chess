package chess.domain.game;

import chess.domain.team.Team;
import java.time.LocalDateTime;

public class Game {

    private final long id;
    private final long whiteId;
    private final long blackId;
    private final Team turn;
    private final boolean isFinished;
    private final LocalDateTime createdTime;

    public Game(final long id, final long whiteId, final long blackId, final Team turn,
        final boolean isFinished,
        final LocalDateTime createdTime) {
        this.id = id;
        this.whiteId = whiteId;
        this.blackId = blackId;
        this.turn = turn;
        this.isFinished = isFinished;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public Long getWhiteId() {
        return whiteId;
    }

    public Long getBlackId() {
        return blackId;
    }

    public Team getTurn() {
        return turn;
    }

    public String getTurnValue() {
        return turn.getValue();
    }

    public boolean isFinished() {
        return isFinished;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}

