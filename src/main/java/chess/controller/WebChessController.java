package chess.controller;

import chess.controller.dto.*;
import chess.service.ChessService;

import java.util.List;

public class WebChessController {

    private final ChessService chessService;

    public WebChessController() {
        this.chessService = new ChessService();
    }

    public PathResponseDto movablePath(final String source, final Long gameId) {
        MovablePathRequestDto movablePathRequestDto = new MovablePathRequestDto(source);
        return chessService.movablePath(movablePathRequestDto, gameId);
    }

    public HistoryResponseDto move(final MoveRequestDto moveRequestDto, final Long gameId) {
        return chessService.move(moveRequestDto, gameId);
    }

    public Long newGame(final NewGameRequestDto newGameRequestDto) {
        return chessService.newGame(newGameRequestDto);
    }

    public List<PieceResponseDto> findPiecesByGameId(final Long gameId) {
        return chessService.findPiecesByGameId(gameId);
    }

    public GameResponseDto findGameByGameId(final Long gameId) {
        return chessService.findGameByGameId(gameId);
    }

    public ScoreResponseDto findScoreByGameId(final Long gameId) {
        return chessService.findScoreByGameId(gameId);
    }

    public StateResponseDto findStateByGameId(final Long gameId) {
        return chessService.findStateByGameId(gameId);
    }

    public List<HistoryResponseDto> findHistoryByGameId(final Long gameId) {
        return chessService.findHistoryByGameId(gameId);
    }
}
