package chess.domain;

@FunctionalInterface
public interface AbstractBoardStateFactory {
    GameBoardState create();
}
