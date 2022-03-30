package chess.controller.menu;

import chess.domain.board.Board;
import chess.domain.board.Result;
import chess.domain.piece.Team;
import chess.view.OutputView;
import java.util.List;

public class Status implements Menu {

    @Override
    public boolean play(Board board) {
        Result score = board.createResult();
        List<Team> gameResult = score.findWinTeam();
        OutputView.printStatus(score.getValue(), gameResult);
        return true;
    }
}
