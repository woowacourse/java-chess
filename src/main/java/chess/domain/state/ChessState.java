package chess.domain.state;

import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.maker.PiecesGenerator;
import chess.domain.position.Position;
import chess.dto.domaintocontroller.GameStatus;

import java.util.Set;

import static chess.domain.ChessGame.INITIAL_STARTING_COLOR;

public abstract class ChessState {

    private static final int INITIAL_GAME_ID = 0;

    protected final ChessGame chessGame;
    protected final ChessGameDao chessGameDao;
    protected final PieceDao pieceDao;

    protected ChessState(final ChessGame chessGame, final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        this.chessGame = chessGame;
        this.chessGameDao = chessGameDao;
        this.pieceDao = pieceDao;
    }

    public static ChessState start(
            final PiecesGenerator piecesGenerator,
            final ChessGameDao chessGameDao,
            final PieceDao pieceDao) {
        return new ChessReady(
                ChessGame.createWith(piecesGenerator, INITIAL_STARTING_COLOR, INITIAL_GAME_ID),
                chessGameDao,
                pieceDao
        );
    }

    public abstract ChessState start(final int roomId);

    public abstract ChessState move(final Position sourcePosition, final Position targetPosition);

    public abstract ChessState end();

    public abstract GameStatus status();

    public abstract Set<Piece> getExistingPieces();

    public abstract boolean isEnd();

}
