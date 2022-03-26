package chess.domain.board.state;

import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class End extends GameStarted {

    public End(Map<Integer, Rank> ranks) {
        super(ranks);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean isBlackTurn() {
        throw new IllegalStateException("게임이 끝나서 턴이 없습니다.");
    }

    @Override
    public BoardState move(Position start, Position target) {
        throw new IllegalStateException("게임이 끝나서 말을 움직일 수 없습니다.");
    }

    @Override
    public BoardState terminate() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }
}
