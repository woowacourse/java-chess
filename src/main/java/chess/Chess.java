package chess;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.view.InputView;
import chess.view.OutputView;

public class Chess {

    private static final String COMMAND_DISTRIBUTOR = " ";
    private static final String INVALID_MOVING_COMMAND = "올바르지 않은 이동 명령입니다.";
    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다."; // TODO: 중복 제거
    private static final int COMMAND = 0;
    private static final int MOVE_COMMAND_LENGTH = 3;
    private static final int STARTING_POINT = 1;
    private static final int DESTINATION = 2;

    public Chess() {

    }

    public void run() {
        OutputView.printStartMessage();
        ChessGame chessGame = new ChessGame(new Board(new CreateCompleteBoardStrategy()));
        while (chessGame.isNotEnd()) {
            proceed(chessGame);
        }
        OutputView.printStatus(chessGame.getStatus());
    }

    private void proceed(ChessGame chessGame) {
        try {
            proceedOnce(chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            proceed(chessGame);
        }
    }

    private void proceedOnce(ChessGame chessGame) {
        final String[] args = InputView.input()
                .split(COMMAND_DISTRIBUTOR, -1);
        if (args.length != 1 && args.length != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException(INVALID_MOVING_COMMAND);
        }
        final Command command = Command.from(args[COMMAND]);
        operate(chessGame, args, command);
    }

    private void operate(ChessGame chessGame, String[] args, Command command) {
        if (command == Command.START) {
            start(chessGame);
            return;
        }
        if (command == Command.MOVE) {
            move(chessGame, args[STARTING_POINT], args[DESTINATION]);
            return;
        }
        if (command == Command.END) {
            chessGame.end();
            return;
        }
        if (command == Command.STATUS) {
            OutputView.printStatus(chessGame.getStatus());
            return;
        }
        throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
    }

    private void start(ChessGame chessGame) {
        chessGame.start();
        OutputView.printBoard(chessGame.getBoard().getPieces());
    }

    private void move(ChessGame chessGame, String startValue, String targetValue) {
        final Position start = Position.from(startValue);
        final Position target = Position.from(targetValue);
        chessGame.move(start, target);
        OutputView.printBoard(chessGame.getBoard().getPieces()); // TODO: 객체 지향 생활 체조 원칙 지키기
    }
}
