package chess.model.member;

public class Member {

    private final int id;
    private final String name;
    private final int roomId;

    public Member(String name) {
        this(0, name, 0);
    }

    public Member(int id, String name, int roomId) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRoomId() {
        return roomId;
    }
}
