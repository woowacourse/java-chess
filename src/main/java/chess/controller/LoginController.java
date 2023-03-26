package chess.controller;

import chess.domain.database.Database;
import chess.domain.database.LoginDao;
import chess.domain.game.User;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class LoginController {

    private final LoginDao loginDao = new LoginDao(Database.PRODUCT);

    public User getUser() {
        OutputView.printWelcomeMessage();
        User user;
        do {
            user = getUserByLoginCommand();
        } while (user == null);
        return user;
    }

    private User getUserByLoginCommand() {
        OutputView.printLoginMessage();
        Command loginCommand = readLoginCommand();
        if (loginCommand == Command.LOGIN) {
            return login();
        }
        return register();
    }

    private Command readLoginCommand() {
        Command command = InputView.readCommand();
        try {
            validateLoginCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readLoginCommand();
        }
    }

    private void validateLoginCommand(Command command) {
        if (command == Command.LOGIN || command == Command.REGISTER) {
            return;
        }
        throw new IllegalArgumentException("아이디가 있으시다면 login, 아이디가 없으시다면 register를 입력해주세요.");
    }

    private User login() {
        OutputView.printIdMessage();
        String userId = InputView.readNext();
        User user = loginDao.getUserById(userId);
        if (user == null) {
            OutputView.printErrorMessage("존재하지 않는 아이디입니다.");
        }
        return user;
    }

    private User register() {
        String userId = readUserId();
        if (isUserIdAlreadyExist(userId)) {
            OutputView.printErrorMessage("이미 사용중인 아이디입니다.");
            return null;
        }
        String nickname = readNickname();
        loginDao.addUser(new User(userId, nickname));
        return loginDao.getUserById(userId);
    }

    private String readUserId() {
        OutputView.printIdInputMessage();
        return InputView.readNext();
    }

    private boolean isUserIdAlreadyExist(String userId) {
        User user = loginDao.getUserById(userId);
        return user != null;
    }

    private String readNickname() {
        OutputView.printNicknameInputMessage();
        return InputView.readNext();
    }
}
