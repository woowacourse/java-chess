package chess.controller.state;

import chess.domain.board.Board;
import chess.dto.ScoreDto;

public interface ChessGameState {
    ChessGameState start();

    ChessGameState move(String from, String to);

    ScoreDto status();

    ChessGameState end();

    Turn getTurn();

    Board getBoard();

    boolean isEnded();
}
