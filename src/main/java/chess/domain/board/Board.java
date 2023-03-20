package chess.domain.board;

public interface Board {

    Board initialize();

    boolean isInitialized();

    boolean isEnd();

    Board move(final String source, final String target);

    GameResult getResult();
}
