package chess.command;

import chess.domain.state.ChessState;

public interface Command {

    ChessState execute(ChessState chessState);
}
