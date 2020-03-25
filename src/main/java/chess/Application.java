package chess;

import domain.commend.CommendType;
import domain.commend.Commend;
import domain.commend.Move;
import domain.pieces.Pieces;
import domain.pieces.PiecesFactory;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.printStart();

        Commend commend = new Commend();
        commend.findCommend(InputView.inputGameCommend());
        if (commend.isStart()) {
            play(commend);
        }
    }

    private static void play(Commend commend) {
        Pieces pieces =  Pieces.of(PiecesFactory.create());
        OutputView.printBoard(pieces);

//        while (commend.isStart()) {
//            String inputCommend = InputView.inputGameCommend();
//            if (commend.findCommend(inputCommend) == CommendType.MOVE) {
//                Move.movePiece(pieces, inputCommend);
//            }
//        }

    }
}
