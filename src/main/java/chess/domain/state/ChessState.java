package chess.domain.state;

import chess.Command;

public interface ChessState {

    ChessState execute(Command command);
}
