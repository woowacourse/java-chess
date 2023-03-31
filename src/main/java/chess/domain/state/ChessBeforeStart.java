package chess.domain.state;

import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.piece.maker.ManualPieceGenerator;
import chess.domain.piece.maker.StartingPiecesGenerator;

public abstract class ChessBeforeStart extends ChessState {

    public static final int NEW_ROOM_ID_OPTION = -1;

    protected ChessBeforeStart(final ChessGame chessGame, final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        super(chessGame, chessGameDao, pieceDao);
    }

    @Override
    public final ChessState start(final int roomId) {
        if (roomId == NEW_ROOM_ID_OPTION) {
            return createRoom();
        }

        return enterRoom(roomId);
    }

    private ChessRunning enterRoom(final int roomId) {
        final ChessGame newChessGame = ChessGame.createWith(
                new ManualPieceGenerator(pieceDao.findAllPieceInGame(roomId)),
                chessGameDao.findCurrentTurnColor(roomId),
                roomId);
        return new ChessRunning(newChessGame, chessGameDao, pieceDao);
    }

    private ChessRunning createRoom() {
        final int roomId = chessGameDao.makeGameRoom(ChessGame.INITIAL_STARTING_COLOR);
        final ChessGame newChessGame = ChessGame.createWith(
                new StartingPiecesGenerator(),
                ChessGame.INITIAL_STARTING_COLOR,
                roomId);
        pieceDao.updatePieces(roomId, newChessGame.getExistingPieces());
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
