package chess.contoller;

import chess.PieceOperator;
import chess.board.Board;
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
                //TODO 점수 계산 로직 구현
            }
            OutputView.printChessBoard();
            gameState = gameState.operateCommand(Command.getByInput(input), Command.getArguments(input));
        }
    }
}
