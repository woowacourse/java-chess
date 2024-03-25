package domain.game;

public class End implements GameCommand {

    @Override
    public void execute(final ChessGame chessGame) {
        chessGame.gameEnd();
    }
}
