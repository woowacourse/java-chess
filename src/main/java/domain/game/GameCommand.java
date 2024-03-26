package domain.game;

public interface GameCommand {
    void execute(ChessGame chessGame);
}
