package chess.domain.game;

import chess.domain.piece.Piece;

import java.util.Map;

public class ChessGame {

    private static final long RUNNING_KING_NUMBER = 2;

    private final Board board;
    private GameStatus gameStatus;

    public ChessGame(Board board) {
        this.board = board;
        this.gameStatus = GameStatus.READY;
    }

    public void inputGameCommand(GameCommand gameCommand) {
        if (gameStatus.isReady()) {
            validateGameCommandWhenReady(gameCommand);
            return;
        }
        if (gameStatus.isRunning()) {
            validateGameCommandWhenRunning(gameCommand);
            return;
        }
        validateGameCommandWhenTerminated(gameCommand);
    }

    private void validateGameCommandWhenReady(GameCommand gameCommand) {
        if (gameCommand == GameCommand.START) {
            gameStatus = GameStatus.RUNNING;
            return;
        }
        throw new IllegalArgumentException("[ERROR] 게임을 먼저 시작해주세요.");
    }

    private void validateGameCommandWhenRunning(GameCommand gameCommand) {
        if (gameCommand == GameCommand.START) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 실행중입니다.");
        }
        if (gameCommand == GameCommand.END) {
            gameStatus = GameStatus.TERMINATED;
        }
    }

    private void validateGameCommandWhenTerminated(GameCommand gameCommand) {
        if (gameCommand == GameCommand.START) {
            throw new IllegalArgumentException("[ERROR] 종료된 게임이므로 시작할 수 없습니다.");
        }
        if (gameCommand == GameCommand.MOVE) {
            throw new IllegalArgumentException("[ERROR] 종료된 게임이므로 움직일 수 없습니다.");
        }
    }

    public boolean isNotTerminated() {
        return !gameStatus.isTerminated();
    }

    public void progress(Position source, Position target) {
        if (gameStatus.isNotRunning()) {
            throw new IllegalArgumentException("[ERROR] 게임이 진행중이 아닙니다.");
        }
        board.move(source, target);
        if (shouldTerminateGame(board.countKingNumber())) {
            gameStatus = GameStatus.TERMINATED;
        }
    }

    private boolean shouldTerminateGame(long kingNumber) {
        return RUNNING_KING_NUMBER != kingNumber;
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
