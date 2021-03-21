package controller.menu;

import domain.ChessGame;
import domain.dto.StatusDto;
import view.OutputView;

public class Status implements Command {

    @Override
    public void execute(String command, ChessGame game) {
        if (game.isWait()) {
            OutputView.gameNotStart();
            return;
        }

        OutputView.showStatus(StatusDto.create(game.piecesScore()));
    }
}
