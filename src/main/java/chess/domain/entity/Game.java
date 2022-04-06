package chess.domain.entity;

public class Game {

    private Long game_id;
    private String game_state;
    private String game_turn;

    public Game(Long game_id, String game_state, String game_turn) {
        this.game_id = game_id;
        this.game_state = game_state;
        this.game_turn = game_turn;
    }

    public Long getGame_id() {
        return game_id;
    }

    public String getGame_state() {
        return game_state;
    }

    public String getGame_turn() {
        return game_turn;
    }
}
