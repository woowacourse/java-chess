package chess;

import chess.domain.chessboard.Chessboard;
import chess.view.InputView;
import chess.view.ResultView;
import chess.view.command.InitialCommand;
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
        InitialCommand initialCommand = inputView.askCommand();
        if (initialCommand.isStart()) {
            play();
        }
        resultView.printGameEnd();
    }

    private void play() {
        Chessboard chessboard = Chessboard.create();
        resultView.printBoard(new ChessboardDto(chessboard));
        playByCommand(chessboard);
    }

    private void playByCommand(final Chessboard chessboard) {
        InitialCommand initialCommand = inputView.askCommand();
        while (initialCommand.isMove()) {
            MoveCommand moveCommand = initialCommand.toMoveCommand();
            chessboard.move(moveCommand.getSource(), moveCommand.getTarget());
            resultView.printBoard(new ChessboardDto(chessboard));
            initialCommand = inputView.askCommand();
        }
    }
}
