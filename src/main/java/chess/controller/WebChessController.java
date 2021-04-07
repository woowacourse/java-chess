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

    public Long newGame(NewGameRequestDto newGameRequestDto) {
        return chessService.newGame(newGameRequestDto);
    }

    public List<PieceResponseDto> findPiecesByGameId(Long gameID) {
        return chessService.findPiecesByGameId(gameID);
    }

    public StateResponseDto gameState() {
        return chessService.gameState();
    }

    public GameResponseDto findGameByGameId(Long gameID) {
        return chessService.findGameByGameId(gameID);
    }

    public ScoreResponseDto findScoreByGameId(Long gameID) {
        return chessService.findScoreByGameId(gameID);
    }

    public StateResponseDto findStateByGameId(Long gameID) {
        return chessService.findStateByGameId(gameID);
    }
}
