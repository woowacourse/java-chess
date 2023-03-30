package chess.domain.state;

import chess.constant.ExceptionCode;
import chess.dao.ChessGameDao;
import chess.dao.DbPieceDao;
import chess.dao.GameRoomDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.maker.DbPieceLoadingGenerator;
import chess.domain.piece.maker.StartingPiecesGenerator;
import chess.domain.position.Position;
import chess.dto.domaintocontroller.GameStatus;

import java.util.Set;

public final class ChessGameOver extends ChessState {

    ChessGameOver(final ChessGame chessGame, final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        super(chessGame, chessGameDao, pieceDao);
    }

    @Override
    public ChessState start() {
        final GameRoomDao gameRoomDao = new GameRoomDao();
        final Set<Integer> exisingRoomNumbers = gameRoomDao.findExisingRoomNumbers();
        if (exisingRoomNumbers.contains(1)) {
            final ChessGame newChessGame = ChessGame.createWith(new DbPieceLoadingGenerator(new DbPieceDao(1)));
            return new ChessRunning(newChessGame, chessGameDao, pieceDao);
        }
        final ChessGame newChessGame = ChessGame.createWith(new StartingPiecesGenerator());
        return new ChessRunning(newChessGame, chessGameDao, pieceDao);
    }

    @Override
    public ChessState move(final Position sourcePosition, final Position targetPosition) {
        throw new IllegalStateException(ExceptionCode.GAME_OVER_STATE.name());
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
