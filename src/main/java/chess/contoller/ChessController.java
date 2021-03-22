package chess.contoller;

import chess.domain.ChessGame;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void play() {
        ChessGame chessGame = new ChessGame(new Board(), new Turn(Team.WHITE));
        OutputView.printStartInfo();
        start(chessGame);
    }

    private void start(ChessGame chessGame) {
        while (chessGame.isContinue()) {
            inputCommandAndExecute(chessGame);
            OutputView.printCurrentTurn(chessGame.currentTurn());
            OutputView.printChessBoard(chessGame.boardDto());
        }
    }

    private void inputCommandAndExecute(ChessGame chessGame) {
        String input = InputView.InputString();
        Command command = Command.commandByInput(input);
        command.execute(chessGame, Command.arguments(input));
        printScoreIfStatus(chessGame, command);
    }

    private void printScoreIfStatus(ChessGame chessGame, Command command) {
        if (command == Command.STATUS) {
            OutputView.printTeamScore(chessGame.score(Team.WHITE), Team.WHITE);
            OutputView.printTeamScore(chessGame.score(Team.BLACK), Team.BLACK);
        }
    }
}
