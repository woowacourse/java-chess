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
        ChessGame chessGame = new ChessGame(new Board(), new Turn());

        ready(chessGame);
        start(chessGame);
    }

    private void ready(ChessGame chessGame) {
        OutputView.printStartInfo();
        String input = InputView.InputString();
        Command.getByInput(input).execute(chessGame, Command.getArguments(input));
    }

    private void start(ChessGame chessGame) {
        while (chessGame.isOnGoing()) {
            OutputView.printChessBoard(chessGame.generateBoardDto());
            String input = InputView.InputString();
            Command command = Command.getByInput(input);
            command.execute(chessGame, Command.getArguments(input));
            printScoreIfStatus(chessGame, command);
        }
    }

    private void printScoreIfStatus(ChessGame chessGame, Command command) {
        if (command == Command.STATUS) {
            OutputView.printTeamScore(chessGame.score(Team.WHITE), Team.WHITE);
            OutputView.printTeamScore(chessGame.score(Team.BLACK), Team.BLACK);
        }
    }
}
