package chess.controller;

import chess.domain.Board;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.Ready;
import chess.dto.RequestDto;
import chess.view.InputOption;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new Ready();
        OutputView.printGameInitMessage();
        while (!chessGame.isFinish()) {
            chessGame = selectMenu(chessGame, InputView.inputOption());
        }
        if (!chessGame.isTerminate()) {
            OutputView.printWinner(chessGame.judgeWinner());
        }
    }

    public ChessGame selectMenu(ChessGame chessGame, RequestDto dto) {
        InputOption option = dto.getInputOption();
        Command command = CommandFactory.playCommand(option);
        try {
            return command.run(chessGame, dto.getFromPosition(), dto.getToPosition());
        } catch (IllegalStateException | IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
        }
        return chessGame;
    }

    public static void showBoard(Board board) {
        OutputView.printInitialChessBoard(board);
    }

    public static void showScore(double whiteScore, double blackScore) {
        OutputView.printScore(whiteScore, blackScore);
    }
}
