package chess;

import chess.domain.ChessBoard;
import chess.dto.CommandRequest;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();

        OutputView.printStartMessage();
        startChess(chessBoard);

        playChess(chessBoard);
    }

    private static void startChess(ChessBoard chessBoard) {
        CommandRequest commandRequest = InputView.inputCommand();
        Command command = commandRequest.getCommand();

        if (command.isStart()) {
            chessBoard.init();
            OutputView.printChessBoard(chessBoard.getBoard());
            return;
        }

        OutputView.printErrorMessage("게임이 시작되지 않았습니다.");
        startChess(chessBoard);
    }

    private static void playChess(ChessBoard chessBoard) {
        CommandRequest commandRequest = InputView.inputCommand();
        Command command = commandRequest.getCommand();

        if (command.isStart()) {
            OutputView.printErrorMessage("게임이 이미 시작되었습니다.");
            playChess(chessBoard);
        }

        if (command.isEnd()) {
            return;
        }

        chessBoard.move(commandRequest.getSource(), commandRequest.getTarget());
        OutputView.printChessBoard(chessBoard.getBoard());
        playChess(chessBoard);
    }
}
