package chess.domain.game.state;

import chess.domain.game.result.GameResult;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import chess.domain.position.ChessBoardFactory;
import chess.domain.position.Position;
import java.util.Map;

public class ReadyGame implements ChessGame {

    private static final String NOT_START_GAME = "게임이 시작되지 않았습니다. 게임을 먼저 시작해주세요.";
    private static final Camp INIT_CAMP = Camp.WHITE;

    @Override
    public ChessGame startGame() {
        ChessBoard chessBoard = ChessBoardFactory.getInitialChessBoard();
        return new RunGame(chessBoard, INIT_CAMP);
    }

    @Override
    public ChessGame move(Position fromPosition, Position toPosition) {
        throw new IllegalStateException(NOT_START_GAME);
    }

    @Override
    public boolean isRunnableGame() {
        return true;
    }

    @Override
    public ChessGame endGame() {
        throw new IllegalStateException(NOT_START_GAME);
    }

    @Override
    public ChessGame status() {
        throw new IllegalStateException(NOT_START_GAME);
    }

    @Override
    public Map<Position, Piece> getPiecesPosition() {
        throw new IllegalStateException(NOT_START_GAME);
    }

    @Override
    public GameResult calculateResult() {
        throw new IllegalStateException(NOT_START_GAME);
    }
}
