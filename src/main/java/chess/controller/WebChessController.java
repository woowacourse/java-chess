package chess.controller;

import chess.controller.dto.MovablePathRequestDto;
import chess.controller.dto.MoveRequestDto;
import chess.controller.dto.PathResponseDto;
import chess.service.ChessService;

public class WebChessController {

    private final ChessService chessService;

    public WebChessController() {
        this.chessService = new ChessService();
    }

    public PathResponseDto movablePath(String source) {
        MovablePathRequestDto movablePathRequestDto = new MovablePathRequestDto(source);
        return chessService.movablePath(movablePathRequestDto);
    }

    public void move(String source, String target) {
        MoveRequestDto moveRequestDto = new MoveRequestDto(source, target);
        chessService.move(moveRequestDto);
    }
}
