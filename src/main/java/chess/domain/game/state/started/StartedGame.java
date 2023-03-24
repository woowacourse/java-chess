package chess.domain.game.state.started;

import chess.domain.game.result.GameResult;
import chess.domain.game.state.ChessGame;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import chess.domain.position.Position;
import java.util.Map;

public abstract class StartedGame implements ChessGame {

    private static final String GAME_ALREADY_START = "게임이 이미 시작되었습니다.";
    private static final String GAME_NOT_END = "게임이 종료되지 않았습니다.";

    protected final ChessBoard chessBoard;
    protected final Camp turnCamp;

    protected StartedGame(ChessBoard chessBoard, Camp turnCamp) {
        this.chessBoard = chessBoard;
        this.turnCamp = turnCamp;
    }

    @Override
    public ChessGame startGame() {
        throw new IllegalStateException(GAME_ALREADY_START);
    }

    @Override
    public Map<Position, Piece> getPiecesPosition() {
        return chessBoard.getPiecesPosition();
    }

    @Override
    public GameResult calculateResult() {
        throw new IllegalStateException(GAME_NOT_END);
    }
}
