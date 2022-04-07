package chess.controller;

import chess.ChessService;
import chess.Member;
import chess.domain.Board;
import chess.dto.BoardDto;
import chess.dto.BoardsDto;
import chess.dto.ResponseDto;
import chess.dto.ScoreDto;
import chess.model.ConsoleBoard;
import chess.model.piece.Color;
import java.util.List;

public class ChessController {

    private final ChessService chessService;

    public ChessController() {
        this.chessService = new ChessService();
    }

    public BoardDto getBoard(int roomId) {
        return chessService.getBoard(roomId);
    }

    public ResponseDto move(int boardId, String source, String target) {
        try {
            chessService.move(source, target, boardId);
        } catch (IllegalArgumentException e) {
            return ResponseDto.of(400, e.getMessage(), chessService.isEnd(boardId));
        }
        return ResponseDto.of(200, null, chessService.isEnd(boardId));
    }

    public ScoreDto score(int roomId) {
        return chessService.status(roomId);
    }

    public BoardsDto getBoards() {
        return chessService.getBoards();
    }

    public int startGame(String roomTitle, String member1, String member2) {
        final Board board = new Board(roomTitle, List.of(new Member(member1), new Member(member2)));
        return chessService.init(board).getId();
    }

    public void end(int roomId) {
        chessService.end(roomId);
    }
}
