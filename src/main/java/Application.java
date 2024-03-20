import controller.InputController;
import java.util.List;
import java.util.Scanner;
import model.Command;
import model.GameBoard;
import point.Moving;
import point.Position;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        InputController inputController = new InputController(inputView, outputView);
        outputView.printStartMessage();
        while (true) {
            String input = inputView.readCommand();
            Command command = Command.from(input);
            if (command == Command.MOVE) {
                List<String> cmd = List.of(input.split(" ")); // TODO 문자로 위치가 주어졌을 때 위치 찾는 테스트 추가하기
                Moving moving = new Moving(Position.from(cmd.get(1)), Position.from(cmd.get(2)));
                //moving.move(gameBoard);
            }
            if (command == Command.END) {
                break;
            }
            outputView.printGameBoard(gameBoard);
        }
    }
}
