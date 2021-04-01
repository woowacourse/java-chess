package chess.domain.game;

public class ChessGameEntity {
    private Long id;
    private String state;

    public ChessGameEntity(final Long id, final String state) {
        this.id = id;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public boolean isPlaying() {
        return state.equals("BlackTurn") || state.equals("WhiteTurn");
    }

}
