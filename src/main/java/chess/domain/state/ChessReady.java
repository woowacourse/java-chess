package chess.domain.state;

import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.domaintocontroller.GameStatus;
import chess.exception.ChessException;
import chess.exception.ExceptionCode;

import java.util.Set;

public final class ChessReady extends ChessBeforeStart {

    ChessReady(final ChessGame chessGame, final ChessGameDao chessGameDao, PieceDao pieceDao) {
        super(chessGame, chessGameDao, pieceDao);
    }

    @Override
    public ChessState move(final Position sourcePosition, final Position targetPosition) {
        throw new ChessException(ExceptionCode.GAME_NOT_INITIALIZED);
    }

    @Override
    public GameStatus status() {
        throw new ChessException(ExceptionCode.GAME_NOT_INITIALIZED);
    }

    @Override
    public Set<Piece> getExistingPieces() {
        throw new ChessException(ExceptionCode.GAME_NOT_INITIALIZED);
    }
}
