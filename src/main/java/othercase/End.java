package othercase;

public class End implements GameCommand {

    @Override
    public void execute(final GameController gameController) {
        gameController.endGame();
    }
}
