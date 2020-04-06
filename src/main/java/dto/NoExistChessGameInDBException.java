package dto;

public class NoExistChessGameInDBException extends RuntimeException{
    private static final String NO_EXIST_CHESS_GAME_IN_DB_MESSAGE = "체스 게임이 DB에 존재하지 않습니다.";

    public NoExistChessGameInDBException() {
        super(NO_EXIST_CHESS_GAME_IN_DB_MESSAGE);
    }
}
