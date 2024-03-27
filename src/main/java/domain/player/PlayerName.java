package domain.player;

import java.util.Objects;

public class PlayerName {

    private final String name;

    public PlayerName(final String name) {
        validateBlank(name);
        this.name = name.trim();
    }

    private void validateBlank(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("플레이어 이름이 빈값일 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PlayerName that = (PlayerName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
