package chess.domain;

@FunctionalInterface
public interface BoardStateFactory {
    LivingPieceGroup create();
}
