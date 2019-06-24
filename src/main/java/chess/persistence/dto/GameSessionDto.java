package chess.persistence.dto;

public class GameSessionDto {
    private long id;
    private String state;
    private String title;

    public static GameSessionDto of(long id, String state, String title) {
        GameSessionDto dto = new GameSessionDto();
        dto.setId(id);
        dto.setState(state);
        dto.setTitle(title);
        return dto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
