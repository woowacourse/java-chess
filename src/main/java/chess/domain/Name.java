package chess.domain;

public class Name {
    private static final int MINIMUM_LENGTH = 2;
    private static final int MAXIMUM_LENGTH = 5;
    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (name.isBlank() || name.length() < MINIMUM_LENGTH || name.length() > MAXIMUM_LENGTH) {
            throw new IllegalArgumentException("이름은 2~5자만 가능합니다.");
        }
    }
}
