package chess.controller.gamestate;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.CommandRequest;
import java.util.Map;

public class Ready implements GameState {

    private static final String WRONG_CALLING_ERROR_MESSAGE = "게임이 실행중이지 않습니다.";

    @Override
    public GameState start(final ChessBoard chessBoard) {
        return new Running(chessBoard);
    }

    @Override
    public GameState play(final CommandRequest commandRequest) {
        throw new IllegalStateException(WRONG_CALLING_ERROR_MESSAGE);
    }

    @Override
    public GameState end() {
        throw new IllegalStateException(WRONG_CALLING_ERROR_MESSAGE);
    }

    @Override
    public Map<Position, Piece> read() {
        throw new IllegalStateException(WRONG_CALLING_ERROR_MESSAGE);
    }

}
