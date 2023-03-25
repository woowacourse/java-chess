package chess.domain.state;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Position;

public class GameEnd extends State{
    protected GameEnd(final ChessGame ChessGame) {
        super(ChessGame);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isGameEnd() {
        return true;
    }

    @Override
    public State move(final Position source, final Position target) {
        throw new UnsupportedOperationException("게임이 종료되었습니다. start, status, end 명령어를 입력해주세요.");
    }

    @Override
    public State start() {
        return new Running(ChessGame.create());
    }

    @Override
    public State end() {
        return new End(chessGame);
    }

    @Override
    public double calculateScore(Color color) {
        return chessGame.calculateScore(color);
    }

    @Override
    public Color getColor() {
        return chessGame.getColor();
    }
}
