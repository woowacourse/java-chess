package chess.service.user;

import chess.repository.user.UserRepositoryImpl;

public class LoginService {

    private final UserRepositoryImpl userRepository;

    public LoginService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public int login(String userName) {
        return userRepository.saveIfNotExist(userName);
    }
}
