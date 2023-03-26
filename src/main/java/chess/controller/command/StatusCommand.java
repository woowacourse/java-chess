package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.Result;
import chess.domain.Score;

import java.util.List;

import static chess.controller.command.CommandType.INVALID_COMMAND_MESSAGE;

public class StatusCommand extends Command{

    private final Result result;

    protected StatusCommand(ChessGame chessGame) {
        super(chessGame, CommandType.STATUS);
        result = chessGame.calculateResult();
    }

    @Override
    public Command execute(List<String> input) {
        CommandType inputCommandType = CommandType.from(input);
        if (inputCommandType == CommandType.STATUS) {
            return executeStatus();
        }
        if (inputCommandType == CommandType.END) {
            return executeEnd();
        }
        throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
    }

    private StatusCommand executeStatus() {
        return this;
    }

    private EndCommand executeEnd() {
        return new EndCommand(new ChessGame(new Board(getChessGameBoards())));
    }
}
