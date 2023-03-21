package chess.controller.gamestate;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.CommandRequest;
import java.util.Map;

public class Running implements GameState {

    private static final String WRONG_CALLING_ERROR_MESSAGE = "이미 시작된 게임입니다.";
    private final ChessBoard chessBoard;

    public Running(final ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState start(final ChessBoard chessBoard) {
        throw new IllegalStateException(WRONG_CALLING_ERROR_MESSAGE);
    }

    @Override
    public GameState play(final CommandRequest commandRequest) {
        chessBoard.move(Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate()));
        return new Running(chessBoard);
    }

    @Override
    public GameState end() {
        return new Ready();
    }

    @Override
    public Map<Position, Piece> read() {
        return chessBoard.piecesByPosition();
    }

}
