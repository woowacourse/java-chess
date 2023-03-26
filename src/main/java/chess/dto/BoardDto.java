package chess.dto;

public class BoardDto {
    private final String source;
    private final String piece;
    private final String roomName;

    public BoardDto(String source, String piece, String roomName) {
        this.source = source;
        this.piece = piece;
        this.roomName = roomName;
    }

    public String getSource() {
        return source;
    }

    public String getPiece() {
        return piece;
    }

    public String getRoomName() {
        return roomName;
    }
}
