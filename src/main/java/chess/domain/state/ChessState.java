package chess.domain.state;

import chess.controller.command.Command;
import chess.domain.ChessGame;
import chess.domain.piece.maker.PiecesGenerator;
import chess.domain.piece.Piece;

import java.util.List;

public abstract class ChessState {

    static final String INVALID_COMMAND_MESSAGE = "유효하지 않은 커멘드가 입력되었습니다.";

    protected final ChessGame chessGame;

    protected ChessState(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public static ChessState start(final PiecesGenerator piecesGenerator) {
        return new ChessReady(ChessGame.createWith(piecesGenerator));
    }

    public abstract ChessState process(Command command);

    public abstract List<Piece> getExistingPieces();

    public abstract boolean isEnd();

}
