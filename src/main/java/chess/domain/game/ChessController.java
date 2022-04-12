package chess.domain.game;

import chess.dao.*;
import chess.domain.member.Member;
import chess.domain.pieces.Color;
import chess.domain.position.Position;
import chess.dto.*;
import chess.mapper.Command;
import chess.service.GameService;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ChessController {

    private static final String MOVE_DELIMITER = " ";
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final GameService gameService;

    public ChessController() {
        final ConnectionManager connectionManager = new ChessConnectionManager();
        gameService = new GameService(
                new ChessBoardDao(connectionManager),
                new ChessPositionDao(connectionManager),
                new ChessPieceDao(connectionManager)
        );
    }

    public int startGame(RequestDto requestDto) {
        final ChessBoard board = new ChessBoard(requestDto.getTitle(), Color.WHITE, List.of(new Member(requestDto.getFirstMemberName()), new Member(requestDto.getSecondMemberName())));
        return gameService.saveBoard(board, new BoardInitializer()).getId();
    }

    public ResponseDto move(int roomId, final String command) {
        if (Command.isMove(command)) {
            return getResponseDto(roomId, command);
        }
        return new ResponseDto(HttpStatus.BAD_REQUEST_400, "잘못된 명령어 입니다.", gameService.isEnd(roomId));
    }

    public StatusDto status(int roomId) {
        return gameService.status(roomId);
    }

    private ResponseDto getResponseDto(int roomId, String command) {
        try {
            movePiece(roomId, Arrays.asList(command.split(MOVE_DELIMITER)));
        } catch (IllegalArgumentException e) {
            return new ResponseDto(HttpStatus.BAD_REQUEST_400, e.getMessage(), gameService.isEnd(roomId));
        }
        return new ResponseDto(HttpStatus.OK_200, "", gameService.isEnd(roomId));
    }

    private void movePiece(int roomId, final List<String> commands) {
        if (commands.size() == MOVE_COMMAND_SIZE) {
            gameService.move(roomId, Position.of(commands.get(SOURCE_INDEX)), Position.of(commands.get(TARGET_INDEX)));
        }
    }

    public BoardDto getBoard(int roomId) {
        return gameService.getBoard(roomId);
    }

    public void end(int roomId) {
        gameService.end(roomId);
    }

    public RoomsDto getRooms() {
        return gameService.getRooms();
    }
}
