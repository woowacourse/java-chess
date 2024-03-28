package chess;

import chess.domain.chessboard.Chessboard;
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
        String command = inputView.askCommand();
        if (Command.isStart(command)) {
            play();
        }
        resultView.printGameEnd();
    }

    private void play() {
        Chessboard chessboard = Chessboard.createChessBoard();
        resultView.printBoard(new ChessboardDto(chessboard));
        playByCommand(chessboard);
    }

    private void playByCommand(final Chessboard chessboard) {
        String command = inputView.askCommand();
        while (Command.isMove(command)) {
            MoveCommand moveCommand = new MoveCommand(command);
            chessboard.move(moveCommand.source(), moveCommand.target());
            resultView.printBoard(new ChessboardDto(chessboard));
            command = inputView.askCommand();
        }
    }
}
