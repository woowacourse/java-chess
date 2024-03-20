//package controller;
//
//import static view.StartOrEndCommand.START;
//
//import domain.game.PieceMover;
//import domain.piece.PieceGenerator;
//import view.InputView;
//import view.OutputView;
//
//public class ChessController {
//    private final InputView inputView;
//    private final OutputView outputView;
//
//    public ChessController(final InputView inputView, final OutputView outputView) {
//        this.inputView = inputView;
//        this.outputView = outputView;
//    }
//
//    public void run() {
//        outputView.printCommandMessage();
//        while (inputView.enterStartOrEnd() == START) {
//            PieceMover mover = new PieceMover();
//            PieceGenerator.generate(mover);
//            outputView.printPieceStatus(mover);
//        }
//    }
//}
