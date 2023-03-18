package chess.domain.state;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.ChessInputDto;

import java.util.Map;

public class StateProcessor {
    private State state;

    private StateProcessor(final State state) {
        this.state = state;
    }

    public static StateProcessor create() {
        final State state = new Ready(Board.create());

        return new StateProcessor(state);
    }

    public final Map<Position, Piece> getBoard() {
        return state.board
                .getBoard();
    }

    public void changeState(State state) {
        this.state = state;
    }

    public boolean isNotEnd() {
        return !state.isEnd();
    }

    public State move(final ChessInputDto chessInputDto) {
        return state.move(chessInputDto.getSource(), chessInputDto.getTarget());
    }

    public State start(final ChessInputDto chessInputDto) {
        return state.start();
    }

    public State end(final ChessInputDto chessInputDto) {
        return state.end();
    }
}
