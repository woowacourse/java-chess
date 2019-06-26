package chess.persistence.dto;

public class BoardStateDto {
    private long id;
    private String type;
    private String coordX;
    private String coordY;

    public static BoardStateDto of(long id, String type, String coordX, String coordY) {
        BoardStateDto dto = new BoardStateDto();
        dto.setId(id);
        dto.setType(type);
        dto.setCoordX(coordX);
        dto.setCoordY(coordY);
        return dto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }
}
