
import domain.board.BoardGenerator;
import domain.board.ChessBoard;
import domain.board.ChessBoardGenerator;
import domain.classification.Command;
import domain.dto.StatusDto;
import view.InputView;
import view.OutputView;

public final class Application {

    public static void main(final String[] args) {
        final BoardGenerator boardGenerator = new ChessBoardGenerator();
        final ChessBoard chessBoard = new ChessBoard(boardGenerator);

        Command command = null;
        startGame(chessBoard, command);
        playGame(chessBoard, command);
    }

    private static void startGame(final ChessBoard chessBoard, Command command) {
        try {
            command = InputView.responseUserStartCommand();
            OutputView.printBoard(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            startGame(chessBoard, command);
        }
    }

    private static void playGame(final ChessBoard chessBoard, Command command) {
        try {
            command = InputView.responseUserCommand();
            if (command.isEnd()){
                return;
            }
            if (command.isStatus()) {
                OutputView.printStatus(new StatusDto(chessBoard));
                playGame(chessBoard, command);
            }
            if (command.isMove()) {
                chessBoard.move(command.getSource(), command.getTarget());
                OutputView.printBoard(chessBoard);
                playGame(chessBoard, command);
            }
            if (!chessBoard.checkKingExist()){
                OutputView.printWinner(chessBoard.calculateWhoWinner());
                return;
            }

        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            playGame(chessBoard, command);
        }
    }
}
