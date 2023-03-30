package chess.domain.state;

import chess.constant.ExceptionCode;
import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.domaintocontroller.GameStatus;

import java.util.Set;

public final class ChessReady extends ChessBeforeStart {

    ChessReady(final ChessGame chessGame, final ChessGameDao chessGameDao, PieceDao pieceDao) {
        super(chessGame, chessGameDao, pieceDao);
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
    public Set<Piece> getExistingPieces() {
        throw new IllegalStateException(ExceptionCode.GAME_NOT_INITIALIZED.name());
    }
}
