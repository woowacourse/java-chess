package chess.domain.piece;

public abstract class Named {
    private final Name name;

    public Named(Name name) {
        this.name = name;
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Named named = (Named)o;

        return getName() != null ? getName().equals(named.getName()) : named.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
