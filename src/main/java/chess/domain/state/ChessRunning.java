package chess.domain.state;

import chess.constant.ExceptionCode;
import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.domaintocontroller.GameStatus;

import java.util.Set;

public class ChessRunning extends ChessState {

    ChessRunning(final ChessGame chessGame, final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        super(chessGame, chessGameDao, pieceDao);
    }

    @Override
    public ChessState start() {
        throw new IllegalStateException(ExceptionCode.GAME_ALREADY_RUNNING.name());
    }

    @Override
    public ChessState move(final Position sourcePosition, final Position targetPosition) {
        chessGame.move(sourcePosition, targetPosition);
        if(chessGame.isKingCaught()){
            return new ChessGameOver(chessGame, chessGameDao, pieceDao);
        }
        return this;
    }

    @Override
    public GameStatus status() {
        return chessGame.getStatus();
    }

    @Override
    public ChessState end() {
        return new ChessEnd(chessGame, chessGameDao, pieceDao);
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
