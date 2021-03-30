package chess.domain.dto;

public class PieceDto {

    private final long id;
    private final long roomId;
    private final char signature;
    private final String team;
    private final String location;

    public PieceDto(long id, long roomId, char signature, String team, String location) {
        this.id = id;
        this.roomId = roomId;
        this.signature = signature;
        this.team = team;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public long getRoomId() {
        return roomId;
    }

    public char getSignature() {
        return signature;
    }

    public String getTeam() {
        return team;
    }

    public String getLocation() {
        return location;
    }
}
