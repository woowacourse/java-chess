package chess.view;

import chess.controller.dto.MoveCommandDto;

public interface InputView {

    boolean askStartCommand();

    MoveCommandDto askRunCommand();
}
