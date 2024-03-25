package chess.view;

public record UserCommand(GameStatus gameStatus, String source, String destination) {

    public UserCommand(GameStatus gameStatus) {
        this(gameStatus, "", "");
    }
}
