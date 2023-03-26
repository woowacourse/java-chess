package chess.domain.player;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {

    private static final Pattern NAME_FORMAT = Pattern.compile("[a-zA-Z]+");
    private static final String INVALID_FORMAT_ERROR_MESSAGE = "참여자 이름은 영문자와 숫자 조합만 가능합니다";
    private static final String INVALID_LENGTH_ERROR_MESSAGE = "참여자 이름의 길이는 2글자 이상, 255자 이하만 가능합니다";
    private static final int NAME_MIN_LENGTH = 2;
    private static final int NAME_MAX_LENGTH = 255;

    private final int id;
    private final String name;

    private Player(final int id, final String name) {
        validate(name);
        this.id = id;
        this.name = name;
    }

    public static Player of(final int id, final String name) {
        return new Player(id, name);
    }

    private void validate(final String name) {
        validateFormat(name);
        validateLength(name);
    }

    private void validateFormat(final String name) {
        if (isNotPlayerName(name)) {
            throw new IllegalArgumentException(INVALID_FORMAT_ERROR_MESSAGE);
        }
    }

    private boolean isNotPlayerName(final String name) {
        return !NAME_FORMAT.matcher(name).matches();
    }

    private void validateLength(final String name) {
        if (isLessLength(name) || isExceedLength(name)) {
            throw new IllegalArgumentException(INVALID_LENGTH_ERROR_MESSAGE);
        }
    }

    private boolean isLessLength(final String name) {
        return NAME_MIN_LENGTH > name.length();
    }

    private boolean isExceedLength(final String name) {
        return NAME_MAX_LENGTH < name.length();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
