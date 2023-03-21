package chess.view;

import chess.domain.commnad.Command;
import chess.domain.commnad.LoadGameCommand;
import java.util.Scanner;

public class InputView {

    private final static Scanner input = new Scanner(System.in);

    public Command readGameCommand() {
        try {
            String gameCommand = input.nextLine();
            return Command.from(gameCommand);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return readGameCommand();
        }
    }

    public LoadGameCommand readStatusOfGame() {
        try {
            System.out.println("새로운 게임 하기 : 1, 저장된 게임 불러오기 : 2");
            String command = input.nextLine();
            return LoadGameCommand.from(command);
        } catch (IllegalArgumentException exception) {
            System.out.println("커맨드가 올바른지 확인하세요.");
            return readStatusOfGame();
        }
    }
}
