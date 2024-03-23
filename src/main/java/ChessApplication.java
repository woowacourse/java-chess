import domain.board.Board;
import domain.board.InitialBoardGenerator;
import domain.game.ChessGame;
import view.InputView;
import view.OutputView;
import view.dto.MovePositionDto;

public class ChessApplication {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final ChessGame chessGame = new ChessGame(Board.generatedBy(new InitialBoardGenerator()));

    public static void main(String[] args) {
        outputView.printStartMessage();
        String command = inputView.readCommand();
        if (START_COMMAND.equals(command)) {
            outputView.printBoard(chessGame.getBoard());
            startTurn();
        }
    }

    private static void startTurn() {
        String gameCommand = inputView.readCommand();
        if (END_COMMAND.equals(gameCommand)) {
            return;
        }
        if (MOVE_COMMAND.startsWith(gameCommand)) {
            Board board = chessGame.startTurn(MovePositionDto.from(gameCommand));
            outputView.printBoard(board);
        }
        startTurn();
    }
}
