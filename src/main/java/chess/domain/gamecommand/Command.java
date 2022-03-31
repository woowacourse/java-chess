package chess.domain.gamecommand;

import chess.view.OutputView;

public interface Command {

    void execute(String rawInputCommand, final OutputView outputView);
}
