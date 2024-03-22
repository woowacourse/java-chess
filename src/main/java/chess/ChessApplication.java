package chess;

import chess.controller.ChessController;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.MovePositionDto;

public class ChessApplication {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final ChessController controller = new ChessController();

    public static void main(String[] args) {
        outputView.printStartMessage();
        String command = inputView.readCommand();
        if (command.equals(START_COMMAND)) {
            outputView.printBoard(controller.getBoard());
            startTurn(Color.WHITE);
        }
    }

    private static void startTurn(Color color) {
        String gameCommand = inputView.readCommand();
        if (gameCommand.equals(END_COMMAND)) {
            return;
        }
        if (gameCommand.startsWith(MOVE_COMMAND)) {
            Board board = controller.move(MovePositionDto.from(gameCommand, color));
            outputView.printBoard(board);
        }
        startTurn(Color.nextColorOf(color));
    }
}
