import controller.InputController;
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
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        InputController inputController = new InputController(inputView, outputView);
        outputView.printStartMessage();
        Camp camp = Camp.WHITE;
        boolean start = false;

        while (true) {
            String input = inputView.readCommand();
            Command command = Command.from(input);
            if (command == Command.START && start) {
                throw new IllegalArgumentException("이미 게임이 진행중입니다.");
            }
            if (command == Command.START && !start) {
                start = true;
            }
            if (command == Command.MOVE && start) {
                List<String> cmd = List.of(input.split(" ")); // TODO 문자로 위치가 주어졌을 때 위치 찾는 테스트 추가하기
                Moving moving = new Moving(Position.from(cmd.get(1)), Position.from(cmd.get(2)));
                gameBoard.move(moving, camp);
                camp = turn(camp);
            }
            if (command == Command.END) {
                break;
            }
            if (!start) {
                throw new IllegalArgumentException("start하고 시작하세요");
            }
            outputView.printGameBoard(GameBoardDto.from(gameBoard));
            System.out.println("현재 턴: " + camp.toString() + "\n");
        }
    }

    private static Camp turn(Camp camp) {
        if (camp == Camp.WHITE) {
            return Camp.BLACK;
        }
        return Camp.WHITE;
    }
}
