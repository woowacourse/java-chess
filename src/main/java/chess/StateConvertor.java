package chess;

import chess.domain.Board;
import chess.domain.game.state.BlackTurn;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.Ready;
import chess.domain.game.state.WhiteTurn;

public class StateConvertor {

    public static ChessGame of(Board board, String state) {
        if ("blackturn".equals(state)) {
            return new BlackTurn(board);
        }
        if ("whiteturn".equals(state)) {
            return new WhiteTurn(board);
        }
        return new Ready();
    }
}
