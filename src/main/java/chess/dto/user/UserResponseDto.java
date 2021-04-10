package chess.dto.user;

import chess.entity.User;
import java.time.LocalDateTime;

public class UserResponseDto {

    private final String name;
    private final long winCount;
    private final long loseCount;
    private final LocalDateTime createdTime;

    private UserResponseDto(final String name, final long winCount, final long loseCount,
        final LocalDateTime createdTime) {
        this.name = name;
        this.winCount = winCount;
        this.loseCount = loseCount;
        this.createdTime = createdTime;
    }

    public static UserResponseDto from(final User user) {
        return new UserResponseDto(
            user.getName(), user.getWinCount(), user.getLoseCount(), user.getCreatedTime()
        );
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
