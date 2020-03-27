package chess;

import domain.board.Board;
import domain.commend.End;
import domain.commend.State;
import domain.pieces.Piece;
import domain.pieces.Pieces;
import domain.pieces.StartPieces;
import view.InputView;
import view.OutputView;

import java.util.Set;

public class WebUIChessApplication {
    public static void main(String[] args) {
        OutputView.printStart();
        Pieces startPieces = new Pieces(new StartPieces().getInstance());
        State state = new End(startPieces);
        while (true) {
            state = state.pushCommend(InputView.inputGameCommend());
            OutputView.printBoard(Board.of(state.getSet()));
        }
    }

}
