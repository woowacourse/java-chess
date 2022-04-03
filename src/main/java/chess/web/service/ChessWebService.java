package chess.web.service;

import chess.console.domain.board.BasicChessBoardGenerator;
import chess.console.domain.board.Board;
import chess.console.domain.board.Position;
import chess.console.domain.state.Ready;
import chess.console.domain.state.State;
import chess.web.dto.MoveCommand;
import chess.web.dto.MoveResponseDto;
import java.util.HashMap;

public class ChessWebService {

    private State state;

    public ChessWebService() {
        this.state = Ready.start(new Board(new HashMap<>()));
    }


    public Board getBoard() {
        return state.getBoard();
    }

    public void initializeState() {
        state = Ready.start(BasicChessBoardGenerator.generator());
    }

    public MoveResponseDto movePiece(MoveCommand command) {
        Position source = Position.of(command.getSource());
        Position destination = Position.of(command.getDestination());
        state = state.movePiece(source, destination);

        return new MoveResponseDto(command.getSource(), command.getDestination(), isFinished());
    }

    private boolean isFinished() {
        return state.isFinished();
    }
}
