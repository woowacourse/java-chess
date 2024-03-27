package chess.controller;

import chess.view.input.InputView;
import chess.view.output.OutputView;

public interface GameState {
    GameState run(InputView inputView, OutputView outputView);
    boolean canContinue();
}
