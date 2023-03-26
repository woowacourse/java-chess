package domain;

public final class Room {
    private Long id = null;
    private final String name;

    public Room(final String name) {
        this.name = name;
    }

    public Room(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
