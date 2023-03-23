import status.Ready;
import view.OutputView;

public final class Application {

    public static void main(String[] args) {
        try {
            new Ready().run();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }
}
