import controller.ChessController;
import domain.board.Board;
import domain.board.InitialBoardGenerator;
import view.InputView;
import view.OutputView;
import view.dto.MovePositionDto;

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
            outputView.printBoard(Board.generatedBy(new InitialBoardGenerator()));

            boolean isEnd = false;
            while (!isEnd) {
                String gameCommand = inputView.readCommand();
                if (gameCommand.equals(END_COMMAND)) {
                    isEnd = true;
                }
                if (gameCommand.startsWith(MOVE_COMMAND)) {
                    MovePositionDto movePositionDto = MovePositionDto.from(gameCommand);
                    Board board = controller.move(movePositionDto);
                    outputView.printBoard(board);
                }
            }
        }
    }
}
