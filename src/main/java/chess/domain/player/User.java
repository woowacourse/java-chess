package chess.domain.player;

import java.util.Objects;

public class User {

    private static final int DEFAULT_LOSE_COUNT = 0;
    private static final int DEFAULT_WIN_COUNT = 0;

    private final String name;
    private final int winCount;
    private final int loseCount;

    public User(String name, int winCount, int loseCount) {
        this.name = name;
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public User(String name) {
        this(name, DEFAULT_WIN_COUNT, DEFAULT_LOSE_COUNT);
    }

    public User win() {
        return new User(name, winCount + 1, loseCount);
    }

    public User lose() {
        return new User(name, winCount, loseCount + 1);
    }

    public boolean isNeverPlayingGame() {
        return (winCount == DEFAULT_WIN_COUNT) && (loseCount == DEFAULT_LOSE_COUNT);
    }

    public String getName() {
        return name;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return winCount == user.winCount &&
                loseCount == user.loseCount &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, winCount, loseCount);
    }
}
