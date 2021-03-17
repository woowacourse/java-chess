package chess.domain;

public class ChessGame {
    private Board board;
    private boolean isRunning;

    public ChessGame() {
        isRunning = true;
    }

    public void endGame() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void initBoard(Board board) {
        this.board =board;
    }

    public Board getBoard() {
        return board;
    }

    public void move(String command) {
        splitSourceAndTarget(command);
        // 전략에 따라 말을 옮기기

    }

    private void splitSourceAndTarget(String command) {
    }
}
