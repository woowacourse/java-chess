import static domain.classification.InputCase.*;

import domain.board.BoardGenerator;
import domain.board.ChessBoard;
import domain.board.ChessBoardGenerator;
import domain.classification.InputCase;
import domain.dto.StatusDto;
import domain.position.Position;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final BoardGenerator boardGenerator = new ChessBoardGenerator();
        final ChessBoard chessBoard = new ChessBoard(boardGenerator);

        InputCase input = ELSE;
        while (!input.equals(START)) {
            try {
                input = InputView.responseUserStartCommand();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

        while (!input.equals(END)) {
            try {
                OutputView.printBoard(chessBoard);
                input = InputView.responseUserCommand();

                if (input.equals(STATUS)) {
                    OutputView.printStatus(new StatusDto(chessBoard));
                }
                if (input.equals(MOVE)) {
                    Position source = InputView.responseSource();
                    Position target = InputView.responseTarget();
                    chessBoard.move(source, target);
                }
                if (!chessBoard.checkKingExist()){
                    OutputView.printWinner(chessBoard.calculateWhoWinner());
                    break;
                }
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
