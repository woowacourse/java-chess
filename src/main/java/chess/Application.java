package chess;

import domain.commend.State;
import domain.move.Move;
import domain.pieces.Pieces;
import domain.pieces.PiecesFactory;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.printStart();
        State state = new State();
        state.changeCommend(InputView.inputGameCommend());
        play(state);

    }

    private static void play(State state) {
        Pieces pieces =  Pieces.of(PiecesFactory.create());

        while (!state.isEnd()) {
            OutputView.printBoard(pieces);
            String inputCommend = InputView.inputCommend();
            state.changeCommend(inputCommend);
            if (state.isMove()) {
                Move.movePiece(state.getTurn(), pieces, inputCommend);
                state.changeTurn();
            }
        }

    }
}
