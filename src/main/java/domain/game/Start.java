package domain.game;

public class Start implements GameCommand {

    @Override
    public void execute(final ChessGame chessGame) {
        if (chessGame.isGameRunning()) {
            throw new IllegalArgumentException("이미 게임이 진행중입니다.");
        }

        chessGame.gameStart();
    }
}
