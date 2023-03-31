package chess.domain.game.command;

import chess.domain.game.state.ChessGame;

public interface ChessGameCommand {

    ChessGame execute(ChessGame chessGame);
}
