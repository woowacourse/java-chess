package chess.domain;

public enum Color {
    Black, White;

    public String convertName(String name) {
        if (this == Black) {
            name = name.toUpperCase();
        }

        return name;
    }
}
