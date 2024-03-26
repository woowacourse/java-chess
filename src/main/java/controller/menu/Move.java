package controller.menu;

import dto.GameBoardDto;
import model.ChessGame;
import model.position.Moving;
import model.position.Position;
import view.OutputView;

public class Move implements Menu {

    private final Moving moving;

    public Move(Position currentPosition, Position nextPosition) {
        this.moving = new Moving(currentPosition, nextPosition);
    }

    @Override
    public void play(ChessGame chessGame, OutputView outputView) {
        chessGame.move(moving);
        if (chessGame.isKingDie()) {
            return;
        }
        printCurrentStatus(chessGame, outputView);
    }

    private void printCurrentStatus(final ChessGame chessGame, final OutputView outputView) {
        outputView.printGameBoard(GameBoardDto.from(chessGame));
        outputView.printCurrentCame(chessGame.getCamp());
    }
}
