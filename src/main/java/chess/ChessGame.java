package chess;

import chess.domain.chessboard.ChessBoard;
import chess.domain.Command;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessGame {
    public void run() {
        OutputView.printStartMessage();
        Command command = Command.getStartCommand(InputView.readCommand());
        ChessBoard chessBoard = ChessBoard.initializeChessBoard();
        while (!command.isEnd()) {
            OutputView.printChessBoard(chessBoard.getChessBoard());
            command = Command.getProcessCommand(InputView.readCommand());
            processGame(command, chessBoard);
        }
    }

    private void processGame(Command command, ChessBoard chessBoard) {
        if (command.isMove()) {
            List<String> sourcePosition = InputView.readPosition();
            Position source = Position.of(sourcePosition.get(0), sourcePosition.get(1));
            List<String> targetPosition = InputView.readPosition();
            Position target = Position.of(targetPosition.get(0), targetPosition.get(1));
            chessBoard.move(source, target);
        }
    }
}
