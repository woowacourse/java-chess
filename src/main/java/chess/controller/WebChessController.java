package chess.controller;

import chess.controller.dto.*;
import chess.service.ChessService;

import java.util.List;

public class WebChessController {

    private final ChessService chessService;

    public WebChessController() {
        this.chessService = new ChessService();
    }

    public PathResponseDto movablePath(String source) {
        MovablePathRequestDto movablePathRequestDto = new MovablePathRequestDto(source);
        return chessService.movablePath(movablePathRequestDto);
    }

    public void move(MoveRequestDto moveRequestDto) {
        chessService.move(moveRequestDto);
    }

    public StatusResponseDto gameStatus() {
        return chessService.gameStatus();
    }

    public void newGame() {
        chessService.newGame();
    }

    public List<PieceResponseDto> boardInfo() {
        return chessService.boardInfo();
    }

    public StateResponseDto gameState() {
        return chessService.gameState();
    }
}
