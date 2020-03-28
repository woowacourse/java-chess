package chess.domain.gamestatus;

public interface GameStatus {

    GameStatus start();

    GameStatus move(String fromPosition, String toPosition);

    String getBoardString();
}
