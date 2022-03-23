package chess.domain;

import chess.GameCommand;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessGame {
    private boolean isRunning;
    private final Board board;

    public ChessGame() {
        this.isRunning = false;
        this.board = new Board();
    }

    public boolean isStarted() {
        return isRunning;
    }

    private void start() {
        if (isRunning) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 실행 중 입니다.");
        }

        Map<Location, Piece> board1 = board.getBoard();

        for (Rank rank : Rank.reverseValues()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (File file : File.values()) {
                stringBuilder.append(board1.get(Location.of(file, rank)).toString());
            }
            System.out.println(stringBuilder);
        }
        isRunning = true;
    }

    public void execute(GameCommand command) {
        if (command == GameCommand.START) {
            start();
        }

        if (command == GameCommand.END) {
            end();
        }
    }

    private void end() {
        if (!isRunning) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 종료되었습니다.");
        }
        isRunning = false;
    }

}
