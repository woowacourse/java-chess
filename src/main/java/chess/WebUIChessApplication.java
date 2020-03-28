package chess;

import domain.board.Board;
import domain.state.End;
import domain.state.State;
import domain.pieces.Pieces;
import domain.pieces.StartPieces;
import view.InputView;
import view.OutputView;

public class WebUIChessApplication {
    public static void main(String[] args) {
        OutputView.printStart();
        Pieces startPieces = new Pieces(new StartPieces().getInstance());
        State state = new End(startPieces);
        while (true) {
            state = state.pushCommend(InputView.inputGameCommend());
            if (state.isPlaying()) {
                OutputView.printBoard(Board.of(state.getSet()));
            }
            if (state.isStatus()) {
                OutputView.printStatus(state.getPieces());
            }
        }
    }

}
