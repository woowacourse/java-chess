package chess.game.state;

import chess.board.Board;
import chess.piece.Color;
import chess.position.Position;

public class WhiteTurn extends TurnState {

    @Override
    public GameState proceedTurn(Board board, Position source, Position destination) {
        board.move(source, destination, Color.WHITE);
        if (board.isKingCaptured(Color.BLACK)) {
            return new TerminatedState();
        }
        return new BlackTurn();
    }
}
