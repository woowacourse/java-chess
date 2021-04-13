package chess.controller.web;

import chess.controller.web.dto.game.GameRequestDto;
import chess.controller.web.dto.game.GameResponseDto;
import chess.controller.web.dto.history.HistoryResponseDto;
import chess.controller.web.dto.move.MoveRequestDto;
import chess.controller.web.dto.move.PathResponseDto;
import chess.controller.web.dto.piece.PieceResponseDto;
import chess.controller.web.dto.score.ScoreResponseDto;
import chess.controller.web.dto.state.StateResponseDto;
import chess.service.ChessService;

import java.util.List;

public class WebChessController {

    private final ChessService chessService;

    public WebChessController() {
        this.chessService = new ChessService();
    }

    public PathResponseDto movablePath(final String source, final Long gameId) {
        return chessService.movablePath(source, gameId);
    }

    public HistoryResponseDto move(final MoveRequestDto moveRequestDto, final Long gameId) {
        return chessService.move(moveRequestDto.toMoveCommand(), gameId);
    }

    public Long newGame(final GameRequestDto gameRequestDto) {
        return chessService.saveGame(gameRequestDto.toGame());
    }

    public List<PieceResponseDto> findPiecesByGameId(final Long gameId) {
        return chessService.findPiecesById(gameId);
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
