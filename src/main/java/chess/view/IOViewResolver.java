package chess.view;

import chess.domain.game.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.dto.ScoreDto;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class IOViewResolver {

    private final InputView inputView;
    private final OutputView outputView;

    public IOViewResolver(final Scanner scanner) {
        this.inputView = new InputView(scanner);
        this.outputView = new OutputView();
    }

    public List<String> readCommand() {
        return inputView.readCommand();
    }

    public void printInitialMessage() {
        outputView.printInitialMessage();
    }

    public void printErrorMessage(final String message) {
        outputView.printErrorMessage(message);
    }

    public void printBoard(final Map<Position, Piece> board) {
        outputView.printBoard(board);
    }

    public void printEndMessage() {
        outputView.printEndMessage();
    }

    public void printTotalScore(final ScoreDto calculateScore) {
        outputView.printTotalScore(calculateScore);
    }

    public void printWinner(final Team winner) {
        outputView.printWinner(winner);
    }
}
