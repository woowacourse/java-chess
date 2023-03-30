package chess.domain.state;

import chess.dao.ChessGameDao;
import chess.dao.DbGameRoomDao;
import chess.dao.GameRoomDao;
import chess.dao.PieceDao;
import chess.dao.connection.MySqlConnectionGenerator;
import chess.domain.ChessGame;
import chess.domain.piece.maker.ManualPieceGenerator;
import chess.domain.piece.maker.StartingPiecesGenerator;

import java.util.Set;

public abstract class ChessBeforeStart extends ChessState{

    protected ChessBeforeStart(final ChessGame chessGame, final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        super(chessGame, chessGameDao, pieceDao);
    }

    @Override
    public final ChessState start() {
        int targetLoadingRoomId = 1;
        int targetNewRoomId = 1;

        final GameRoomDao gameRoomDao = new DbGameRoomDao(new MySqlConnectionGenerator());
        final Set<Integer> exisingRoomNumbers = gameRoomDao.findExistingRoomNumbers();
        if (exisingRoomNumbers.contains(targetLoadingRoomId)) {
            final ChessGame newChessGame = ChessGame.createWith(
                    new ManualPieceGenerator(pieceDao.findAllPieceInGame(targetLoadingRoomId)),
                    chessGameDao.findCurrentTurnColor(targetLoadingRoomId),
                    targetLoadingRoomId);
            return new ChessRunning(newChessGame, chessGameDao, pieceDao);
        }

        final ChessGame newChessGame = ChessGame.createWith(
                new StartingPiecesGenerator(),
                ChessGame.INITIAL_STARTING_COLOR,
                targetNewRoomId);
        chessGameDao.makeGameRoom(targetNewRoomId, ChessGame.INITIAL_STARTING_COLOR);
        return new ChessRunning(newChessGame, chessGameDao, pieceDao);
    }

    @Override
    public final ChessState end() {
        return new ChessEnd(chessGame, chessGameDao, pieceDao);
    }

    @Override
    public final boolean isEnd() {
        return false;
    }
}
