package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    public void run() {
        ChessGame chessGame = new ChessGame(new Board(InitBoardGenerator.initLines()));
        Commands commands = Commands.initCommands(chessGame);
        while (chessGame.isNotFinished()) {
            playChess(chessGame, commands);
        }
        // 게임 종료 후 3단계 승패코드
    }

    private void playChess(ChessGame chessGame, Commands commands) {
        try {
            String input = InputView.command();
            Command command = commands.matchedCommand(input);
            command.execution(input);
            printGameStatus(chessGame);
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorException(e.getMessage());
        }
    }

    private void printGameStatus(ChessGame chessGame) {
//        OutputView.printBoard(chessGame.board());
    }
}
