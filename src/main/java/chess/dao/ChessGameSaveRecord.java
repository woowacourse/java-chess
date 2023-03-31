package chess.dao;

public class ChessGameSaveRecord {

    public final int id;
    public final String roomName;
    public final String time;

    ChessGameSaveRecord(final int id, final String roomName, final String time){
        this.id = id;
        this.roomName = roomName;
        this.time = time;
    }
}
