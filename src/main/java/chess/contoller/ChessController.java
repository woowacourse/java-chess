package chess.contoller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.command.Command;
import chess.domain.gamestate.GameState;
import chess.domain.gamestate.Ready;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    public void play() {
        ChessGame chessGame = new ChessGame(new Board());
        GameState gameState = new Ready(chessGame);

        gameState = ready(gameState);
        start(chessGame, gameState);
    }

    private GameState ready(GameState gameState) {
        OutputView.printStartInfo();
        List<String> input = InputView.InputString();
        Command command = Command.getByInput(input.get(0));
        gameState = gameState.operateCommand(command, Command.getArguments(input));
        return gameState;
    }

    private void start(ChessGame chessGame, GameState gameState) {
        while (gameState.isRunning()) {
            OutputView.printChessBoard(chessGame.generateBoardDto());
            List<String>  input = InputView.InputString();
            Command command = Command.getByInput(input.get(0));
            gameState = gameState.operateCommand(command, Command.getArguments(input));
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
