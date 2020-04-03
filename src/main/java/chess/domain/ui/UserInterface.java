package chess.domain.ui;

import chess.domain.piece.position.MovingFlow;
import chess.ui.Command;

public interface UserInterface {
    MovingFlow inputMovingFlow();

    Command inputStart();

    Command inputStatus();
}
