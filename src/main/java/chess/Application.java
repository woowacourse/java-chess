package chess;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessService service = new ChessService();
        Board board;
        Command command;

        OutputView.printStart();
        do {
            command = Command.from(InputView.receiveCommand());
            if (command.isStart()) {
                board = service.initialize();
                OutputView.printBoard(board);
            }
        } while (command.isNotEnd());
    }
}
