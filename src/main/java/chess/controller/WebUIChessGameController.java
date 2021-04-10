package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.game.ChessGame;
import chess.domain.game.state.State;
import chess.domain.game.state.StateConverter;
import chess.domain.piece.Piece;
import chess.dto.ChessGameDto;
import chess.dto.MovableRequestDto;
import chess.dto.MovableResponseDto;
import chess.dto.MoveRequestDto;
import chess.dto.ScoreDto;
import chess.dto.SearchResultDto;
import chess.dto.SquareDto;
import chess.dto.UserIdsDto;
import chess.utils.DtoAssembler;
import java.util.ArrayList;
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
        ChessGame chessGame = chessGameByDto(chessGameDto);
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

    public MovableResponseDto movablePositions(final ChessGameDto chessGameDto,
        final MovableRequestDto movableDto) {
        ChessGame chessGame = chessGameByDto(chessGameDto);
        List<Position> positions = chessGame.movablePath(movableDto.position());
        return DtoAssembler.movableResponse(positions);
    }

    public ChessGameDto movedChessGame(final ChessGameDto chessGameDto,
        final MoveRequestDto moveDto) {
        ChessGame chessGame = chessGameByDto(chessGameDto);
        Position source = Position.of(moveDto.getSource());
        Position target = Position.of(moveDto.getTarget());
        chessGame.move(source, target);
        return DtoAssembler.chessGameDto(chessGame);
    }

    public ScoreDto score(final ChessGameDto chessGameDto) {
        ChessGame chessGame = chessGameByDto(chessGameDto);
        return DtoAssembler.scoreDto(chessGame.score());
    }

    public List<SearchResultDto> searchResult(final List<ChessGameDto> chessGameDtos,
        final List<UserIdsDto> userIdsDtos) {
        List<ScoreDto> scoreDtos = new ArrayList<>();
        List<String> states = new ArrayList<>();

        for (ChessGameDto chessGameDto : chessGameDtos) {
            ChessGame chessGame = chessGameByDto(chessGameDto);
            scoreDtos.add(DtoAssembler.scoreDto(chessGame.score()));
            states.add(chessGameDto.getState());
        }

        return DtoAssembler.searchResultDto(states, userIdsDtos, scoreDtos);
    }

    private ChessGame chessGameByDto(ChessGameDto chessGameDto) {
        Board board = board(chessGameDto.getSquareDtos());
        StateConverter stateConverter = new StateConverter(board);
        State state = stateConverter.matchedState(chessGameDto.getState());
        return new ChessGame(state);
    }
}
