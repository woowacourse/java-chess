package domain.game.state;

import domain.board.ChessBoard;
import domain.position.Position;

public class Prepare extends GameState {
    public Prepare(ChessBoard board) {
        super(board);
    }

    @Override
    public GameState start() {
        return new WhitePlay(chessBoard());
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new IllegalStateException("게임 시작 전입니다.");
    }

    @Override
    public GameState end() {
        throw new IllegalStateException("게임 시작 전입니다.");
    }
}
