package controller;

import domain.commend.CommendType;
import domain.commend.State;
import domain.pieces.Pieces;
import domain.pieces.PiecesFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import view.InputView;
import view.OutputView;

public class ChessGameController {

    private static ChessGameController chessGameController = new ChessGameController();

    private ChessGameController() {}

    public static ChessGameController getInstance() {
        return chessGameController;
    }

    public static void gameChess() {
        OutputView.printStart();
        Pieces pieces = Pieces.of(PiecesFactory.create());
        State state = State.of(pieces);
        Map<CommendType, Consumer<String>> commends = new HashMap<>();
        init(state, commends);
        commend(commends, InputView.inputGameCommend());
        play(state, commends);
    }

    private static void init(State state, Map<CommendType, Consumer<String>> commends) {
        commends.put(CommendType.START, (input -> state.start()));
        commends.put(CommendType.END, (input -> state.end()));
        commends.put(CommendType.MOVE, (input -> state.move(input)));
        commends.put(CommendType.STATUS, (input -> OutputView.printScore(state.status())));
    }

    private static void play(State state, Map<CommendType, Consumer<String>> commends) {
        while (!state.isFinished()) {
            OutputView.printBoard(state.getPieces());
            commend(commends, InputView.inputCommend());
        }
    }

    private static void commend(Map<CommendType, Consumer<String>> commends, String input) {
        commends.get(CommendType.find(input)).accept(input);
    }

}
