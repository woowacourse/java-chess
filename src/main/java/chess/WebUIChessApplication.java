package chess;

import domain.board.Board;
import domain.state.Ended;
import domain.state.State;
import domain.pieces.Pieces;
import domain.pieces.StartPieces;
import view.InputView;
import view.OutputView;

public class WebUIChessApplication {
    public static void main(String[] args) {
        OutputView.printStart();
        Pieces startPieces = new Pieces(new StartPieces().getInstance());
        State state = new Ended(startPieces);
        while (true) {
            state = state.pushCommend(InputView.inputGameCommend());
            printIfPlaying(state);
            printIfStatus(state);
        }
    }

    private static void printIfPlaying(State state) {
        if (state.isPlaying()) {
            OutputView.printBoard(Board.of(state.getSet()));
        }
    }

    private static void printIfStatus(State state) {
        if (state.isReported()) {
            OutputView.printStatus(state.getPieces());
        }
    }
}
