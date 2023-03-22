package chess.domain.state;

import chess.controller.command.Command;
import chess.controller.command.Type;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Set;

import static chess.controller.command.Command.MOVE_CURRENT_POSITION_INDEX;
import static chess.controller.command.Command.MOVE_TARGET_POSITION_INDEX;

public class ChessRunning extends ChessState {

    static final String CHESS_ALREADY_RUNNING_MESSAGE = "게임이 이미 진행중입니다.";


    ChessRunning(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public ChessState process(final Command command) {
        if (command.is(Type.START)) {
            throw new IllegalStateException(CHESS_ALREADY_RUNNING_MESSAGE);
        }
        if (command.is(Type.MOVE)) {
            processMove(command);
            return this;
        }
        if (command.is(Type.END)) {
            return new ChessEnd(chessGame);
        }

        throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
    }

    private void processMove(final Command command) {
        final Position currentPosition = generatePositionBy(command.getParameterAt(MOVE_CURRENT_POSITION_INDEX));
        final Position targetPosition = generatePositionBy(command.getParameterAt(MOVE_TARGET_POSITION_INDEX));
        chessGame.move(currentPosition, targetPosition);
    }

    private Position generatePositionBy(String fileRankInput) {
        final String fileCode = String.valueOf(fileRankInput.charAt(0));
        final String rankCode = String.valueOf(fileRankInput.charAt(1));

        return Position.of(File.findByCode(fileCode), Rank.findByCode(rankCode));
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
