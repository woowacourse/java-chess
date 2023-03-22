package chess.domain.state;

import chess.controller.command.Command;
import chess.controller.command.Type;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.maker.StartingPiecesGenerator;

import java.util.Set;

public final class ChessReady extends ChessState {

    static final String CHESS_NOT_INITIALIZED_MESSAGE = "아직 게임이 생성되지 않았습니다.";

    ChessReady(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public ChessState process(final Command command) {
        if (command.is(Type.START)) {
            final ChessGame newChessGame = ChessGame.createWith(new StartingPiecesGenerator());
            return new ChessRunning(newChessGame);
        }
        if (command.is(Type.MOVE)) {
            throw new IllegalStateException(CHESS_NOT_INITIALIZED_MESSAGE);
        }
        if(command.is(Type.END)){
            return new ChessEnd(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
    }

    @Override
    public Set<Piece> getExistingPieces() {
        throw new IllegalStateException(CHESS_NOT_INITIALIZED_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
