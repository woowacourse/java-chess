package chess.controller;

import chess.ChessService;
import chess.dto.BoardDto;
import chess.dto.RoomsDto;
import chess.dto.ResponseDto;
import chess.dto.ScoreDto;
import org.eclipse.jetty.http.HttpStatus;

public class ChessController {

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    public BoardDto getBoard(int roomId) {
        return chessService.getBoard(roomId);
    }

    public ResponseDto move(int roomId, String source, String target) {
        try {
            chessService.move(source, target, roomId);
        } catch (IllegalArgumentException e) {
            return ResponseDto.of(HttpStatus.BAD_REQUEST_400, e.getMessage(), chessService.isEnd(roomId));
        }
        return ResponseDto.of(HttpStatus.OK_200, null, chessService.isEnd(roomId));
    }

    public ScoreDto score(int roomId) {
        return chessService.status(roomId);
    }

    public RoomsDto getRooms() {
        return chessService.getRooms();
    }

    public int startGame(String roomTitle, String member1, String member2) {
        return chessService.init(roomTitle, member1, member2).getId();
    }

    public void end(int roomId) {
        chessService.end(roomId);
    }
}
