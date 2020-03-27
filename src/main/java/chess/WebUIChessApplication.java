package chess;

import domain.board.Board;
import domain.commend.End;
import domain.commend.State;
import domain.pieces.Pieces;
import domain.pieces.StartPieces;
import view.InputView;
import view.OutputView;

public class WebUIChessApplication {
    public static void main(String[] args) {
        OutputView.printStart();
        State state = new End(Pieces.of(new StartPieces()));
        while (true) {
            state = state.pushCommend(InputView.inputGameCommend());
            OutputView.printBoard(Board.of(state.getSet()));
        }
    }

}
