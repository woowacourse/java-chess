package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.game.ChessGame;
import chess.domain.game.state.StateConverter;
import chess.domain.game.state.State;
import chess.domain.piece.Piece;
import chess.dto.ChessGameDto;
import chess.dto.SquareDto;
import chess.utils.DtoAssembler;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WebUIChessGameController {

    public ChessGameDto chessGame() {
        return DtoAssembler.chessGameDto(new ChessGame(new Board()));
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

    public ChessGameDto startedChessGame(final ChessGameDto chessGameDto) {
        Board board = board(chessGameDto.getSquareDtos());
        StateConverter stateConverter = new StateConverter(board);
        State state = stateConverter.matchedState(chessGameDto.getState());
        ChessGame chessGame = new ChessGame(state);
        chessGame.start();
        return DtoAssembler.chessGameDto(chessGame);
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
