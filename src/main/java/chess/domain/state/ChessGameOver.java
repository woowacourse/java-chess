package chess.domain.state;

import chess.constant.ExceptionCode;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.maker.StartingPiecesGenerator;
import chess.domain.position.Position;
import chess.dto.domaintocontroller.GameStatus;

import java.util.Set;

public final class ChessGameOver extends ChessState {

    ChessGameOver(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public ChessState start() {
        return new ChessRunning(ChessGame.createWith(new StartingPiecesGenerator()));
    }

    @Override
    public ChessState move(final Position sourcePosition, final Position targetPosition) {
        throw new IllegalStateException(ExceptionCode.GAME_OVER_STATE.name());
    }

    @Override
    public GameStatus status() {
        return chessGame.getStatus();
    }

    @Override
    public ChessState end() {
        return new ChessEnd(chessGame);
    }

    @Override
    public Set<Piece> getExistingPieces() {
        return chessGame.getExistingPieces();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
