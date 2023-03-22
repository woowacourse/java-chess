package chess.domain.state;

import chess.constant.ExceptionCode;
import chess.controller.command.Command;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;

import java.util.Set;

public final class ChessEnd extends ChessState {

    ChessEnd(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public ChessState process(final Command command) {
        throw new IllegalStateException(ExceptionCode.GAME_END.name());
    }

    @Override
    public Set<Piece> getExistingPieces() {
        return chessGame.getExistingPieces();
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
