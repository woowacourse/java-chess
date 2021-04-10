package chess.service.exception;

import chess.entity.User;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(final Class<User> dataType) {
        super(String.format("%s 데이터를 찾을 수 없습니다.", dataType.getName()));
    }
}
