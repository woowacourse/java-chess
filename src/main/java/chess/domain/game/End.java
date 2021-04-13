package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;

public class End extends Idle {

    public End(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void move(final Position source, final Position target) {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public void end() {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public void ready() {
        chessGame.changeState(new Ready(new ChessGame(new Board(PieceFactory.createPieces()))));
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
