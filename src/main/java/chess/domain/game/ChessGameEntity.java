package chess.domain.game;

public class ChessGameEntity {

    private String roomID;
    private String turn;

    public ChessGameEntity(String roomID, String turn) {
        this.roomID = roomID;
        this.turn = turn;
    }

    public ChessGameEntity() {

    }

    public String getRoomID() {
        return roomID;
    }

    public String getTurn() {
        return turn;
    }
}
