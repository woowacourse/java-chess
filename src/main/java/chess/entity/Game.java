package chess.entity;

import chess.domain.team.Team;
import java.time.LocalDateTime;

public class Game {

    private final Long id;
    private final Long whiteId;
    private final Long blackId;
    private final Team turn;
    private final Boolean isFinished;
    private final LocalDateTime createdTime;

    public Game(final Long id, final Long whiteId, final Long blackId, final Team turn,
        final Boolean isFinished, final LocalDateTime createdTime) {

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

    public String getTurnValue() {
        return turn.getValue();
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}

