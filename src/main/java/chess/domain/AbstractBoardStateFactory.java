package chess.domain;

@FunctionalInterface
public interface AbstractBoardStateFactory {
    LivingPieceGroup create();
}
