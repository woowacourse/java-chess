package chess.domain.game;

@FunctionalInterface
public interface Operate {
	boolean apply(ChessGame chessGame, Operations operations);
}
