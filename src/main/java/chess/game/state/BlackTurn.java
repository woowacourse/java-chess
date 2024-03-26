package chess.game.state;

import chess.board.Board;
import chess.piece.Color;
import chess.position.Position;

public class BlackTurn extends TurnState {

    @Override
    public GameState proceedTurn(Board board, Position source, Position destination) {
        board.move(source, destination, Color.BLACK);
        if (board.isKingCaptured(Color.WHITE)) {
            return new TerminatedState();
        }
        return new WhiteTurn();
    }
}
