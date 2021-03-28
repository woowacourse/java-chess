package chess.db.domain.game;

public class ChessGameResponseDTO {
    private final Long id;
    private final String title;

    public ChessGameResponseDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
