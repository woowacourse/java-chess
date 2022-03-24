package chess.domain.board.state;

import chess.domain.board.Rank;
import chess.domain.piece.Position;

public interface BoardState {

    boolean isEnd();

    boolean isBlackTurn();

    boolean isBlackWin();

    BoardState move(Position start, Position target);

    Rank getRank(int rankLine);
}
