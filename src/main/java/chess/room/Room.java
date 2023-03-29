package chess.room;

public class Room {
    private final int roomId;
    private int turn;

    public Room(final int roomId) {
        this.roomId = roomId;
        turn = 1;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getTurn() {
        return turn;
    }

    public void putTurn(int turn) {
        this.turn = turn + 1;
    }

    public void reset() {
        this.turn = 1;
    }

    public void nextTurn() {
        this.turn++;
    }
}
