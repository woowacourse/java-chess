package chess.view;

import chess.controller.dto.ContinueCommandDto;
import chess.controller.dto.StartCommandDto;

public interface InputView {

    StartCommandDto askStartCommand();

    ContinueCommandDto askContinueCommand();
}
