package chess.contoller;

import chess.PieceOperator;
import chess.board.Board;
import chess.board.Team;
import chess.command.Command;
import chess.gamestate.GameState;
import chess.gamestate.Ready;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void play() {
        Board board = new Board();
        PieceOperator pieceOperator = new PieceOperator(board);
        GameState gameState = new Ready(pieceOperator);

        OutputView.printStartInfo();
        String input = InputView.InputString();
        gameState = gameState.operateCommand(Command.getByInput(input), Command.getArguments(input));
        OutputView.printChessBoard();

        while (gameState.isRunning()) {
            input = InputView.InputString();
            Command command = Command.getByInput(input);
            if (command == Command.STATUS) {
                OutputView.printTeamScore(pieceOperator.score(Team.WHITE), Team.WHITE);
                OutputView.printTeamScore(pieceOperator.score(Team.BLACK), Team.BLACK);
            }
            OutputView.printChessBoard();
            gameState = gameState.operateCommand(Command.getByInput(input), Command.getArguments(input));
        }
    }
}
