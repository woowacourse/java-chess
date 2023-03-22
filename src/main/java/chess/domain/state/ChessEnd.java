package chess.domain.state;

import chess.controller.command.Command;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;

import java.util.Set;

public final class ChessEnd extends ChessState {

    static final String GAME_END_MESSAGE = "체스가 이미 종료되었습니다.";

    ChessEnd(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public ChessState process(final Command command) {
        throw new IllegalStateException(GAME_END_MESSAGE);
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
