package chess.model.board.state;

import chess.controller.GameCommand;
import chess.model.ChessGame;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public class Playing implements GameState {

    private final ChessGame chessGame;

    public Playing(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public GameState execute(final GameCommand gameCommand, final Position source, final Position target) {
        if (gameCommand.isMove()) {
            chessGame.move(source, target);
        }
        return this;
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return chessGame.getBoard();
    }
}
