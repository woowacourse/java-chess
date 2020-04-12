package chess.view;

import chess.dto.ContinueCommandDto;
import chess.dto.StartCommandDto;

public interface InputView {

    StartCommandDto askStartCommand();

    ContinueCommandDto askContinueCommand();
}
