package chess.domain2;

public enum Color {
    BLACK("BLACK"),
    WHITE("WHITE");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
