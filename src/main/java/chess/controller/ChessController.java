package chess.controller;

import static chess.view.InputView.inputCommand;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printException;
import static chess.view.OutputView.printGameEndMessage;
import static chess.view.OutputView.printGameStartMessage;

import chess.domain.piece.Piece;
import chess.domain.state.ResultType;
import chess.domain.state.Start;
import chess.domain.state.State;
import chess.domain.state.Status;
import chess.domain.team.Team;
import chess.dto.PiecesDto;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessController {

    public void play() {
        printGameStartMessage();
        State state = new Start();
        while (state.isRunning()) {
            actWithCommand(state);
            boolean isSuccessful = actWithResponse(state);
            state = progress(state, isSuccessful);
        }
        printGameEndMessage();
    }

    private void actWithCommand(State state) {
        try {
            runNeedsCommand(state);
        } catch (RuntimeException e) {
            printException(e.getMessage());
            actWithCommand(state);
        }
    }

    private void runNeedsCommand(State state) {
        if (!state.needsParam()) {
            return;
        }
        state.receive(inputCommand());
    }

    private boolean actWithResponse(State state) {
        try {
            response(state);
            return true;
        } catch (RuntimeException e) {
            printException(e.getMessage());
            return false;
        }
    }

    private void response(State state) {
        if (ResultType.BOARD.equals(state.resultType())) {
            responseBoard(state);
        }
        if (ResultType.SCORE.equals(state.resultType())) {
            responseScore(state);
        }
    }

    private void responseBoard(State state) {
        List<?> result = (List<?>) state.result();
        List<Piece> pieces = result.stream()
            .map(Piece.class::cast)
            .collect(Collectors.toList());

        printBoard(PiecesDto.from(pieces));
    }

    private void responseScore(State state) {
        Map<Team, Double> result = Status.class.cast(state).result();
        OutputView.printScore(result.get(Team.BLACK), result.get(Team.WHITE));
    }

    private State progress(State state, boolean isSuccess) {
        if (isSuccess) {
            return state.next();
        }
        return state.before();
    }
}
