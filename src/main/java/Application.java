import chess.controller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;

// TODO : 전체적으로 지역 변수 및 메소드 분리
public class Application {
    public static void main(String[] args) {
        ChessController controller = new ChessController(InputView.getInstance(), OutputView.getInstance());
        controller.run();
    }
}
