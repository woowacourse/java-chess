package chess.web.service;

import chess.console.domain.board.BasicChessBoardGenerator;
import chess.console.domain.board.Board;
import chess.console.domain.board.Position;
import chess.console.domain.board.Score;
import chess.console.domain.command.StatusResult;
import chess.console.domain.piece.Color;
import chess.console.domain.state.Ready;
import chess.console.domain.state.State;
import chess.web.dto.ApiResult;
import chess.web.dto.MoveCommand;
import chess.web.dto.MoveResponseDto;
import java.util.HashMap;
import java.util.Map;

public class ChessWebService {

    private State state;

    public ChessWebService() {
        this.state = Ready.start(new Board(new HashMap<>()));
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public ApiResult getStatus() {
        Map<Color, Score> score = state.getScore();
        return ApiResult.succeed(new StatusResult(score));
    }

    public void initializeState() {
        state = Ready.start(BasicChessBoardGenerator.generator());
    }

    public ApiResult movePiece(MoveCommand command) {
        Position source = Position.of(command.getSource());
        Position destination = Position.of(command.getDestination());
        try {
            state = state.movePiece(source, destination);
            return ApiResult.succeed(new MoveResponseDto(command.getSource(), command.getDestination(), isFinished()));
        } catch (RuntimeException e) {
            return ApiResult.failed(e.getMessage());
        }
    }

    private boolean isFinished() {
        return state.isFinished();
    }
}
