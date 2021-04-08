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

    public HistoryResponseDto move(MoveRequestDto moveRequestDto, Long gameId) {
        return chessService.move(moveRequestDto, gameId);
    }

    public Long newGame(NewGameRequestDto newGameRequestDto) {
        return chessService.newGame(newGameRequestDto);
    }

    public List<PieceResponseDto> findPiecesByGameId(Long gameId) {
        return chessService.findPiecesByGameId(gameId);
    }

    public GameResponseDto findGameByGameId(Long gameId) {
        return chessService.findGameByGameId(gameId);
    }

    public ScoreResponseDto findScoreByGameId(Long gameId) {
        return chessService.findScoreByGameId(gameId);
    }

    public StateResponseDto findStateByGameId(Long gameId) {
        return chessService.findStateByGameId(gameId);
    }

    public List<HistoryResponseDto> findHistoryByGameId(Long gameId) {
        return chessService.findHistoryByGameId(gameId);
    }
}
