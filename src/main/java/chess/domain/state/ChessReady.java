package chess.domain.state;

import chess.constant.ExceptionCode;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.maker.StartingPiecesGenerator;
import chess.domain.position.Position;
import chess.dto.domaintocontroller.GameStatus;

import java.util.Set;

public final class ChessReady extends ChessState {

    ChessReady(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public ChessState start() {
        final ChessGame newChessGame = ChessGame.createWith(new StartingPiecesGenerator());
        return new ChessRunning(newChessGame);
    }

    @Override
    public ChessState move(final Position sourcePosition, final Position targetPosition) {
        throw new IllegalStateException(ExceptionCode.GAME_NOT_INITIALIZED.name());
    }

    @Override
    public GameStatus status() {
        throw new IllegalStateException(ExceptionCode.GAME_NOT_INITIALIZED.name());
    }

    @Override
    public ChessState end() {
        return new ChessEnd(chessGame);
    }

    @Override
    public Set<Piece> getExistingPieces() {
        throw new IllegalStateException(ExceptionCode.GAME_NOT_INITIALIZED.name());
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
