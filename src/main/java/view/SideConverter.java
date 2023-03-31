package view;

public enum SideConverter {
    WHITE("WHITE"),
    BLACK("BLACK");

    private final String sideName;

    SideConverter(String sideName) {
        this.sideName = sideName;
    }

    public String getSideName() {
        return sideName;
    }
}
