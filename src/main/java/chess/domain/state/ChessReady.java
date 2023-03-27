package chess.domain.state;

import chess.constant.ExceptionCode;
import chess.dao.DbChessGameDao;
import chess.dao.DbPieceDao;
import chess.dao.GameRoomDao;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.maker.DbPieceLoadingGenerator;
import chess.domain.piece.maker.StartingPiecesGenerator;
import chess.domain.position.Position;
import chess.dto.domaintocontroller.GameStatus;

import java.util.Set;

public final class ChessReady extends ChessState {

    ChessReady(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public ChessState start() {
        final GameRoomDao gameRoomDao = new GameRoomDao();
        final Set<Integer> exisingRoomNumbers = gameRoomDao.findExisingRoomNumbers();
        if (exisingRoomNumbers.contains(1)) {
            final ChessGame newChessGame = ChessGame.loadWith(
                    new DbPieceLoadingGenerator(new DbPieceDao(1)),
                    new DbChessGameDao(1),
                    new DbPieceDao(1));
            return new ChessRunning(newChessGame);
        }
        final ChessGame newChessGame = ChessGame.createWith(
                new StartingPiecesGenerator(),
                new DbChessGameDao(1),
                new DbPieceDao(1));
        return new ChessRunning(newChessGame);
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
    public ChessState end() {
        return new ChessEnd(chessGame);
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
