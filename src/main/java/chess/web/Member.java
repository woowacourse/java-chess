package chess.web;

public class Member {

    private final String id;
    private final String name;
    private final double score;

    public Member(final String id, final String name, final double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    //2. getter도 다 가지고
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Member{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
