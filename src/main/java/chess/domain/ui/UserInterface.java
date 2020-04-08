package chess.domain.ui;

import chess.domain.piece.position.MovingFlow;
import chess.ui.Command;

public interface UserInterface {
    //todo refac
    MovingFlow inputMovingFlow();

    Command inputStart();

    Command inputStatus();
}
