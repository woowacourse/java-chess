package chess.controller;

import chess.controller.dto.*;
import chess.service.ChessService;

import java.util.List;

public class WebChessController {

    private final ChessService chessService;

    public WebChessController() {
        this.chessService = new ChessService();
    }

    public PathResponseDto movablePath(String source, Long gameId) {
        MovablePathRequestDto movablePathRequestDto = new MovablePathRequestDto(source);
        return chessService.movablePath(movablePathRequestDto, gameId);
    }

    public void move(MoveRequestDto moveRequestDto, Long gameId) {
        chessService.move(moveRequestDto, gameId);
    }

    public Long newGame(NewGameRequestDto newGameRequestDto) {
        return chessService.newGame(newGameRequestDto);
    }

    public List<PieceResponseDto> findPiecesByGameId(Long gameID) {
        return chessService.findPiecesByGameId(gameID);
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
