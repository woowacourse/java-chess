package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;
import chess.domain.piece.Team;
import java.util.Map;

public final class Ready implements State {
    @Override
    public State start() {
        return new White();
    }

    @Override
    public State stop() {
        return new End();
    }

    @Override
    public Map<Team, Double> status(ChessBoard chessBoard) {
        throw new IllegalArgumentException("지금은 점수를 계산할 수 없습니다.");
    }

    @Override
    public State changeTurn(Command command, ChessBoard chessBoard) {
        return new Ready();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
