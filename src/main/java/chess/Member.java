package chess;

public class Member {

    private final String id;
    private final String name;

    public Member(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    //2. getter도 다 가지고
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Member{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
