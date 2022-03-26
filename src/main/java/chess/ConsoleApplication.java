package chess;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.generator.EmptyBoardGenerator;
import chess.dto.CommandRequest;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard(new EmptyBoardGenerator());

        OutputView.printStartMessage();
        startChess(chessBoard);

        repeatPlayChess(chessBoard);
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

    private static void repeatPlayChess(ChessBoard chessBoard) {
        try {
            playChess(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            repeatPlayChess(chessBoard);
        }
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

        if (command.isStatus()) {
            double whiteScore = chessBoard.calculateScore(Color.WHITE);
            double blackScore = chessBoard.calculateScore(Color.BLACK);
            OutputView.printStatusMessage(whiteScore, blackScore);
            playChess(chessBoard);
        }

        chessBoard.move(commandRequest.getSource(), commandRequest.getTarget());
        OutputView.printChessBoard(chessBoard.getBoard());

        if (chessBoard.isFinished()) {
            Color winner = chessBoard.getTurn();
            System.out.println(winner + "가 승리하였습니다.");
            return;
        }

        playChess(chessBoard);
    }
}
