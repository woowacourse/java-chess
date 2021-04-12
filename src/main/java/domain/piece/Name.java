package domain.piece;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

public class Name {
    public static final int CHARACTER_LENGTH = 1;

    private static final Map<Character, Name> CACHE = new WeakHashMap<>();
    public static final Name EMPTY = new Name('.');
    private final char value;

    private Name(char value) {
        this.value = value;
    }

    public static Name of(String name, boolean isUpperCase) {
        if (name.length() != CHARACTER_LENGTH) {
            throw new IllegalArgumentException("기물의 이름은 한자리만 가능합니다.");
        }

        if (name.charAt(0) != EMPTY.value && !validCase(isUpperCase, name.charAt(0))) {
            throw new IllegalArgumentException("알맞는 기물의 이름을 입력하세요.(검정말 : 대문자, 흰말 : 소문자)");
        }
        return CACHE.computeIfAbsent(name.charAt(0), Name::new);
    }

    public String getName() {
        return String.valueOf(value);
    }

    private static boolean validCase(boolean isUpperCase, char name) {
        if (isUpperCase) {
            return Character.isUpperCase(name);
        }
        return Character.isLowerCase(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return value == name.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
