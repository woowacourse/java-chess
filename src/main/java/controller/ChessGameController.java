package controller;

import domain.commend.CommendType;
import domain.commend.State;
import domain.pieces.Pieces;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import view.InputView;
import view.OutputView;

public class ChessGameController {
    private State state;
    private Map<CommendType, Consumer<String>> commends = new HashMap<>();

    public ChessGameController(Pieces pieces) {
        state = State.of(pieces);
        commends.put(CommendType.START, (input -> state.start()));
        commends.put(CommendType.END, (input -> state.end()));
        commends.put(CommendType.MOVE, (input -> state.move(input)));
        commends.put(CommendType.STATUS, (input -> OutputView.printScore(state.status())));
    }

    public void gameChess() {
        commend(InputView.inputGameCommend());
        play();
    }

    private void play() {
        while (!state.isFinished()) {
            OutputView.printBoard(state .getPieces());
            commend(InputView.inputCommend());
        }
    }

    private void commend(String input) {
        commends.get(CommendType.find(input)).accept(input);
    }
}
