package chess.repository;

import chess.dto.NameDto;
import chess.dto.UserDto;

public interface UserDao {

    void save(final NameDto nameDto);

    UserDto findByName(final NameDto nameDto);

    void deleteAll();
}
