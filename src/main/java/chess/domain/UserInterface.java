package chess.domain;

import chess.domain.position.MovingFlow;
import chess.ui.Command;

public interface UserInterface {
    MovingFlow inputMovingFlow();
    Command inputStart();
}
