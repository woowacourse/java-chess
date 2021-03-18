package chess.gamestate;

public class Finished implements GameState {

    @Override
    public GameState operateCommand(String input) {
        throw new IllegalArgumentException("올바르지 않은 입력입니다.");
    }
}
