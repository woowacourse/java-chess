package othercase;

public class Start implements GameCommand {

    @Override
    public void execute(final GameController gameController) {
        gameController.gameStart();
    }
}
