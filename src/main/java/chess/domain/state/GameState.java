package chess.domain.state;

import chess.domain.board.Rank;
import chess.domain.piece.Position;

public interface GameState {

    boolean isEnd();

    boolean isBlackTurn();

    Winner findWinner();

    GameState move(Position start, Position target);

    Rank getRank(int rankLine);

    GameState terminate();

    double calculateBlackScore();

    double calculateWhiteScore();

    End judgeWinner();

    Playing judgeTurn();

    String findTurn();
}
