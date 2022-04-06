package web.dto;

public class PieceDto {

    private final String roomName;
    private final String position;
    private final String pieceType;
    private final String pieceColor;

    public PieceDto(String roomName, String position, String pieceType, String pieceColor) {
        this.roomName = roomName;
        this.position = position;
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getPosition() {
        return position;
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getPieceColor() {
        return pieceColor;
    }
}
