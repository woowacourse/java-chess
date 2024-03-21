import dto.GameBoardDto;
import java.util.List;
import java.util.Scanner;
import model.Camp;
import model.Command;
import model.GameBoard;
import model.position.Moving;
import model.position.Position;
import view.InputView;
import view.OutputView;

public class Application {

    private static final InputView inputView = new InputView(new Scanner(System.in));
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();
        outputView.printStartMessage();
        Camp camp = Camp.WHITE;
        boolean start = false;

        while (true) {
            String input = inputView.readCommand();
            Command command = Command.from(input);
            if (command == Command.START && start) {
                throw new IllegalArgumentException("이미 게임이 진행중입니다.");
            }

            if (command == Command.START) {
                start = true;
            }

            if (!start) {
                throw new IllegalArgumentException("start하고 시작하세요");
            }

            if (command == Command.MOVE) {
                Moving moving = getMoving(input);
                gameBoard.move(moving, camp);
                camp = camp.toggle();
            }
            if (command == Command.END) {
                break;
            }

            outputView.printGameBoard(GameBoardDto.from(gameBoard));
            outputView.printCurrentCame(camp);

        }
    }

    private static Moving getMoving(final String input) {
        List<String> cmd = List.of(input.split(" "));
        return new Moving(Position.from(cmd.get(1)), Position.from(cmd.get(2)));
    }
}
