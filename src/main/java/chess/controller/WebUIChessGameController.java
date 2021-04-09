package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.game.ChessGame;
import chess.domain.game.state.StateConverter;
import chess.domain.game.state.State;
import chess.domain.piece.Piece;
import chess.dto.SquareDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WebUIChessGameController {

    public ChessGame chessGame() {
        return new ChessGame(new Board());
    }

    public ChessGame chessGame(List<String> commandsStrings) {
        ChessGame chessGame = new ChessGame(new Board());
        Commands commands = Commands.initCommands(chessGame);

        for (String commandString : commandsStrings) {
            Command command = commands.matchedCommand(commandString);
            command.execution(commandString);
        }

        return chessGame;
    }

    public ChessGame chessGame(final String stateString, final List<SquareDto> squareDtos) {
        Board board = board(squareDtos);
        StateConverter stateConverter = new StateConverter(board);
        State state = stateConverter.matchedState(stateString);
        return new ChessGame(state);
    }

    private Board board(List<SquareDto> squareDtos) {
        Map<Position, Piece> squares = new LinkedHashMap<>();

        for (SquareDto squareDto : squareDtos) {
            Position position = Position.of(squareDto.getPosition());
            Piece piece = Piece.of(squareDto.getPiece());
            squares.put(position, piece);
        }

        return new Board(squares);
    }
}
