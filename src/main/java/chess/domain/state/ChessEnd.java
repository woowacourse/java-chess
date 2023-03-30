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

public final class ChessEnd extends ChessState {

    ChessEnd(final ChessGame chessGame, final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        super(chessGame, chessGameDao, pieceDao);
    }

    @Override
    public ChessState start() {
        throw new ChessException(ExceptionCode.GAME_END);
    }

    @Override
    public ChessState move(final Position sourcePosition, final Position targetPosition) {
        throw new ChessException(ExceptionCode.GAME_END);
    }

    @Override
    public GameStatus status() {
        throw new ChessException(ExceptionCode.GAME_END);
    }

    @Override
    public ChessState end() {
        throw new ChessException(ExceptionCode.GAME_END);
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
