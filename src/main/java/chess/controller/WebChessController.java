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

    public List<PieceResponseDto> findPiecesBygameId(Long gameId) {
        return chessService.findPiecesBygameId(gameId);
    }

    public GameResponseDto findGameBygameId(Long gameId) {
        return chessService.findGameBygameId(gameId);
    }

    public ScoreResponseDto findScoreBygameId(Long gameId) {
        return chessService.findScoreBygameId(gameId);
    }

    public StateResponseDto findStateBygameId(Long gameId) {
        return chessService.findStateBygameId(gameId);
    }

    public List<HistoryResponseDto> findHistoryBygameId(Long gameId) {
        return chessService.findHistoryBygameId(gameId);
    }
}
