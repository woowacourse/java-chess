package chess.domain.game;

import chess.dao.Member;
import chess.domain.pieces.Color;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.ResponseDto;
import chess.dto.StatusDto;
import chess.mapper.Command;
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
        gameService = new GameService();
    }

    public int startGame(String roomTitle, String member1, String member2) {
        final Board board = new Board(roomTitle, Color.WHITE, List.of(new Member(member1), new Member(member2)));
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

    public BoardsDto getBoards() {
        return gameService.getBoards();
    }
}
