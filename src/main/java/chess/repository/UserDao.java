package chess.repository;

import chess.domain.user.User;
import chess.dto.NameDto;

public interface UserDao {

    void save(final NameDto nameDto);

    User findByName(final NameDto nameDto);

    void deleteAll();
}
