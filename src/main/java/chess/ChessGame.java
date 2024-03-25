package chess;

import static chess.exception.ExceptionHandler.retry;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.ChessboardFactory;
import chess.view.InputView;
import chess.view.ResultView;
import chess.view.command.Command;
import chess.view.command.MoveCommand;
import chess.view.dto.ChessboardDto;

public class ChessGame {

    private final InputView inputView;
    private final ResultView resultView;

    public ChessGame(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        resultView.printGameStartMessage();
        Command command = inputView.askCommand();
        validateStartCommand(command);
        if (command.isStart()) {
            play();
        }
        resultView.printGameEnd();
    }

    private void validateStartCommand(final Command command) {
        if (!(command.isStart() || command.isEnd())) {
            throw new IllegalArgumentException("시작 또는 종료 명령어를 입력해주세요.");
        }
    }

    private void play() {
        Chessboard chessboard = ChessboardFactory.create();
        resultView.printBoard(new ChessboardDto(chessboard));
        retry(() -> playByCommand(chessboard, inputView.askCommand()));
    }

    private void playByCommand(final Chessboard chessboard, final Command command) {
        if (!command.isMove()) {
            return;
        }
        MoveCommand moveCommand = command.toMoveCommand();
        chessboard.move(moveCommand.getSource(), moveCommand.getTarget());
        resultView.printBoard(new ChessboardDto(chessboard));
        playByCommand(chessboard, inputView.askCommand());
    }
}
