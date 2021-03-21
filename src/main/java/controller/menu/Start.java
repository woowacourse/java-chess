package controller.menu;

import domain.ChessGame;
import domain.piece.objects.PieceFactory;
import view.OutputView;

public class Start implements Command {
    @Override
    public void execute(String command, ChessGame game) {
        if (!game.isWait()) {
            OutputView.alreadyStartGame();
            return;
        }
        game.start(PieceFactory.createPieces());
        OutputView.showBoard(game.getBoard());
    }
}
