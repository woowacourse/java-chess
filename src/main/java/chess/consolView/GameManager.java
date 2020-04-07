package chess.consolView;

import chess.domain.board.BoardGenerator;
import chess.domain.manager.ChessManager;

import java.util.Objects;

public class GameManager {
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    public static final String DELIMITER = " ";
    private ChessManager chessManager;
    private boolean isNotEnd;

    public GameManager() {
        isNotEnd = true;
    }

    public void runGame() {
        OutputView.showAllCommand();
        while (isNotEnd) {
            action();
        }
    }

    public void action() {
        String input = InputView.inputCommand();
        Command command = Command.findBy(input);
        if(command == Command.START) {
            start();
        }
        if (command == Command.END) {
            end();
        }
        if (Objects.isNull(chessManager)) {
            return;
        }
        if (command == Command.MOVE) {
            move(input);
        }
        if (command == Command.STATUS) {
            status();
        }
        OutputView.showChessBoard(this.chessManager.getChessBoard());
    }

    private void start() {
        this.chessManager = new ChessManager(BoardGenerator.create());
    }

    private void end() {
        isNotEnd = false;
    }

    private void move(String command) {
        String source = command.split(DELIMITER)[SOURCE_INDEX];
        String target = command.split(DELIMITER)[TARGET_INDEX];
        chessManager.move(source, target);
        isNotEnd = chessManager.isKingAlive();
    }

    private void status() {
        OutputView.showScore(this.chessManager.getCurrentTeam(),
                this.chessManager.calculateCurrentTeamScore());
    }
}
