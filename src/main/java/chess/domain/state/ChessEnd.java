package chess.domain.state;

import chess.constant.ExceptionCode;
import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.domaintocontroller.GameStatus;

import java.util.Set;

public final class ChessEnd extends ChessState {

    ChessEnd(final ChessGame chessGame, final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        super(chessGame, chessGameDao, pieceDao);
    }

    @Override
    public ChessState start() {
        throw new IllegalStateException(ExceptionCode.GAME_END.name());
    }

    @Override
    public ChessState move(final Position sourcePosition, final Position targetPosition) {
        throw new IllegalStateException(ExceptionCode.GAME_END.name());
    }

    @Override
    public GameStatus status() {
        throw new IllegalStateException(ExceptionCode.GAME_END.name());
    }

    @Override
    public ChessState end() {
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
