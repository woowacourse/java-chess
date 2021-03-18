package chess.gamestate;

public interface GameState {
    GameState operateCommand(String input);
}
