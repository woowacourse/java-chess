package chess.Controller;

import chess.Controller.command.ParsedCommand;
import chess.Controller.command.PieceCommandFactory;
import chess.Controller.command.ScoreCommandFactory;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.Controller.dto.StateDto;
import chess.domain.board.Board;

public class ChessController {

    private final Board board;

    public ChessController(Board board) {
        this.board = board;
    }

    public PiecesDto doActionAboutPieces(final ParsedCommand parsedCommand) {
        return PieceCommandFactory.from(parsedCommand.getCommand())
                .doCommandAction(parsedCommand, board);
    }

    public PiecesDto getPieces() {
        return PiecesDto.fromEntity(board);
    }

    public ScoreDto doActionAboutScore(final ParsedCommand parsedCommand) {
        return ScoreCommandFactory.from(parsedCommand.getCommand())
                .doCommandAction(parsedCommand, board);
    }

    public StateDto getCurrentStatus() {
        return StateDto.fromEntity(board);
    }
}
