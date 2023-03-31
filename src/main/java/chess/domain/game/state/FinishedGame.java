package chess.domain.game.state;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import chess.domain.position.Position;
import java.util.Map;

public abstract class FinishedGame implements ChessGame {

    private static final String GAME_ALREADY_END = "이미 게임이 종료 되었습니다.";

    protected final ChessBoard chessBoard;

    protected FinishedGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public ChessGame move(Position fromPosition, Position toPosition) {
        throw new IllegalStateException(GAME_ALREADY_END);
    }

    @Override
    public ChessGame endGame() {
        throw new IllegalStateException(GAME_ALREADY_END);
    }

    @Override
    public boolean isRunnableGame() {
        return false;
    }

    @Override
    public ChessGame status() {
        throw new IllegalStateException(GAME_ALREADY_END);
    }

    @Override
    public ChessGame startGame() {
        throw new IllegalStateException(GAME_ALREADY_END);
    }

    @Override
    public Map<Position, Piece> getPiecesPosition() {
        return this.chessBoard.getPiecesPosition();
    }

    @Override
    public Camp getCurrentCamp() {
        throw new IllegalStateException(GAME_ALREADY_END);
    }
}
