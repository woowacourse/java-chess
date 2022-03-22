package chess2.domain.square;

public class Empty implements Square{

    private static final String DISPLAY = ".";

    @Override
    public String display() {
        return DISPLAY;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass();
    }

    @Override
    public String toString() {
        return "Empty";
    }
}
