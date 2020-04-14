package chess.domain.gamestate;

import chess.domain.board.Board;

import java.util.List;

public interface GameState {
    GameState move(String fromPosition, String toPosition);

    List<List<String>> getBoardForPrint();

    GameState finish();

    Board getBoard();

    double getWhiteTeamScore();

    double getBlackTeamScore();

    boolean isRunning();
}
