package chess.console;

import static chess.console.view.InputView.COMMAND_INDEX;
import static chess.console.view.InputView.FROM_POSITION_INDEX;
import static chess.console.view.InputView.TO_POSITION_INDEX;

import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.domain.board.generator.BoardGenerator;
import chess.domain.position.Position;
import java.util.List;

public class ChessGame {

    private boolean isStart = false;
    private Board board;

    public void play(BoardGenerator boardGenerator) {
        OutputView.printGuideMessage();
        boolean canPlay = true;

        while (canPlay) {
            canPlay = isExceedPlay(inputCommand(), boardGenerator);
            printBoard();
        }
    }

    private boolean isExceedPlay(List<String> inputs, BoardGenerator boardGenerator) {
        Command command = Command.of(inputs.get(COMMAND_INDEX));

        if (command.isEnd() || isCheckmate()) {
            return false;
        }

        if (command.isStart()) {
            isStart = true;
            board = boardGenerator.create();
            return true;
        }

        if (command.isMove()) {
            move(inputs);
            return true;
        }

        if (command.isStatus()) {
            status();
        }

        return true;
    }

    private List<String> inputCommand() {
        try {
            List<String> inputs = InputView.inputCommand();
            Command.of(inputs.get(COMMAND_INDEX));
            return inputs;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputCommand();
        }
    }

    private void move(List<String> inputs) {
        try {
            validRunning();
            board.move(Position.of(inputs.get(FROM_POSITION_INDEX))
                    , Position.of(inputs.get(TO_POSITION_INDEX)));

        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void status() {
        try {
            validRunning();
            Score score = board.createResult();
            OutputView.printStatus(score.getValue(), score.findWinTeam());

        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void validRunning() {
        validStart();
        validCheck();
    }

    private void validStart() {
        if (!isStart) {
            throw new IllegalStateException("체스 게임을 시작해야 합니다.");
        }
    }

    private void validCheck() {
        if (board != null && board.check()) {
            throw new IllegalStateException("현재 check 상황입니다.");
        }
    }

    private boolean isCheckmate() {
        return board != null && board.checkmate();
    }

    private void printBoard() {
        if (board != null) {
            OutputView.printBoard(board.getBoard());
        }
    }
}
