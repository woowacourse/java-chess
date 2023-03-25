package chess.domain.state;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.maker.PiecesGenerator;
import chess.domain.position.Position;
import chess.dto.domaintocontroller.GameStatus;

import java.util.Set;

public abstract class ChessState {

    protected final ChessGame chessGame;

    protected ChessState(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public static ChessState start(final PiecesGenerator piecesGenerator) {
        return new ChessReady(ChessGame.createWith(piecesGenerator));
    }

    public abstract ChessState start();

    public abstract ChessState move(final Position sourcePosition, final Position targetPosition);

    public abstract ChessState end();

    public abstract GameStatus status();

    public abstract Set<Piece> getExistingPieces();

    public abstract boolean isEnd();

}
