package chess.controller.state;

import chess.view.InputView;
import chess.view.OutputView;

public interface State {

    State execute(InputView inputView, OutputView outputView);

    boolean isRunning();
}
