package chess;

import java.util.Objects;

public class Camp {
    private final CampType type;

    private Camp(final CampType type) {
        this.type = type;
    }

    public static Camp create(final String position) {
        validate(position);
        return new Camp(checkCamp(position.charAt(0)));
    }

    private static void validate(final String position) {
        validateLength(position);
        validateRange(position);
    }

    private static void validateLength(final String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private static void validateRange(final String position) {
        final String lowerCasePosition = position.toLowerCase();
        if (lowerCasePosition.charAt(0) < 'a' || lowerCasePosition.charAt(0) > 'h') {
            throw new IllegalArgumentException("이동 위치 열은 a~h 사이여야 합니다.");
        }
        if (lowerCasePosition.charAt(1) < '1' || lowerCasePosition.charAt(1) > '8') {
            throw new IllegalArgumentException("이동 위치 행은 1~8 사이여야 합니다.");
        }
    }

    private static CampType checkCamp(final char columnPosition) {
        return CampType.divide(columnPosition);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Camp camp = (Camp) o;
        return type == camp.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
