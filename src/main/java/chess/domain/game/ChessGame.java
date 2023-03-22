package chess.domain.game;

public class ChessGame {

    private static final long RUNNING_KING_NUMBER = 2;
    private final Board board;
    private GameStatus gameStatus;

    public ChessGame(Board board) {
        this.board = board;
        this.gameStatus = GameStatus.RUNNING;
    }

    public void progress(Position source, Position target) {
        if (gameStatus.isTerminated()) {
            throw new IllegalArgumentException("[ERROR] 게임이 끝나서 움직일 수 없습니다.");
        }
        board.move(source, target);

        if (shouldTerminateGame(board.countKingNumber())) {
            gameStatus = GameStatus.TERMINATED;
        }
    }

    private boolean shouldTerminateGame(long kingNumber) {
        return RUNNING_KING_NUMBER != kingNumber;
    }
}
