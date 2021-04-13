package chess.domain.game;

public abstract class Running extends Started {

    public Running(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void ready() {
        throw new IllegalArgumentException("이미 게임이 실행 중입니다.");
    }
}
