package chess.domain.user;

import java.time.LocalDateTime;

public class User {

    private final long id;
    private final String name;
    private final long winCount;
    private final long loseCount;
    private final LocalDateTime createdTime;

    public User(final long id, final String name, final long winCount, final long loseCount,
        final LocalDateTime createdTime) {
        this.id = id;
        this.name = name;
        this.winCount = winCount;
        this.loseCount = loseCount;
        this.createdTime = createdTime;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getWinCount() {
        return winCount;
    }

    public long getLoseCount() {
        return loseCount;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}

