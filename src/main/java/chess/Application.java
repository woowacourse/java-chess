package chess;

import chess.controller.Controller;
import chess.repository.jdbc.JdbcUserDao;
import chess.service.UserService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        UserService userService = new UserService(new JdbcUserDao());

        Controller controller = Controller.create(inputView, outputView);
        controller.run();
//        new ChessGameController(inputView, outputView, userService).start();

        scanner.close();
    }
}
