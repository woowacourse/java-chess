package domain.piece;

import java.io.Serializable;

public enum Color implements Serializable {

    BLACK("검은색"),
    WHITE("흰색"),
    NONE("무색");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public boolean isBlack() {
        return Color.BLACK.equals(this);
    }

}
