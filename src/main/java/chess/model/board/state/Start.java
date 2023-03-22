package chess.model.board.state;

import chess.controller.GameCommand;
import chess.model.ChessGame;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public class Start implements GameState {

    private final ChessGame chessGame;

    private Start() {
        this.chessGame = new ChessGame();
    }

    public static GameState from(final GameCommand gameCommand) {
        if (gameCommand.isMove()) {
            throw new IllegalArgumentException("시작하기 전에 move를 호출 할 수 없습니다.");
        }

        if (gameCommand.isEnd()) {
            return new End();
        }

        return new Playing(new ChessGame());
    }


    @Override
    public GameState execute(final GameCommand gameCommand, final Position source, final Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public GameState isGameEnd() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return chessGame.getBoard();
    }
}
