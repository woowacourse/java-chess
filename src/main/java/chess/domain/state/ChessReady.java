package chess.domain.state;

import chess.constant.ExceptionCode;
import chess.controller.command.Command;
import chess.controller.command.Type;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.maker.StartingPiecesGenerator;

import java.util.Set;

public final class ChessReady extends ChessState {

    ChessReady(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public ChessState process(final Command command) {
        if (command.is(Type.START)) {
            final ChessGame newChessGame = ChessGame.createWith(new StartingPiecesGenerator());
            return new ChessRunning(newChessGame);
        }
        if (command.is(Type.MOVE)) {
            throw new IllegalStateException(ExceptionCode.GAME_NOT_INITIALIZED.name());
        }
        if(command.is(Type.END)){
            return new ChessEnd(chessGame);
        }
        throw new IllegalArgumentException(ExceptionCode.INVALID_COMMAND.name());
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
