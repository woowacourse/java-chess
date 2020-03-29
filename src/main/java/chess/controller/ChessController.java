package chess.controller;

import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.service.ChessService;

public class ChessController {

    private ChessService chessService = new ChessService();

    public boolean isEnd() {
        return chessService.isEnd();
    }

    public synchronized ResponseDto run(RequestDto requestDto) {
        switch (requestDto.getCommand()) {
            case START:
                return chessService.start();
            case END:
                return chessService.end();
            case MOVE:
                return chessService.move(requestDto.getParameter());
            case STATUS:
                return chessService.status();
            default:
                throw new IllegalArgumentException("잘못된 명령어입니다.");
        }
    }
}
