package dto;

public class NoExistChessGameInDBException extends RuntimeException{
    private static final String NoExistChessGameInDbMessage = "체스 게임이 DB에 존재하지 않습니다.";

    public NoExistChessGameInDBException() {
        super(NoExistChessGameInDbMessage);
    }
}
