package domain.piece;

public enum Color {

    BLACK("흑", true),
    WHITE("백", false);

    private final String name;
    private final boolean isBlack;

    Color(String name, boolean isBlack) {
        this.name = name;
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public String getName() {
        return name;
    }
}
