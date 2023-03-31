package chess.repository.user;

public interface UserRepository {

    int saveIfNotExist(String userName);
}
