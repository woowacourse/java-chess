package chess.service;

import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.ResponseDTO;
import chess.domain.board.setting.BoardSetting;
import chess.domain.game.ChessGame;
import chess.domain.player.Scores;

public class ChessService {
    private ChessGame chessGame;
    private final BoardSetting boardSetting;

    public ChessService(BoardSetting boardSetting) {
        this.boardSetting = boardSetting;
    }

    public ResponseDTO getResponseWhenRequestStart() {
        chessGame = new ChessGame(boardSetting);
        Scores scores = chessGame.getScores();
        return new ResponseDTO(
            chessGame.boardCellsStatus(),
            chessGame.currentTurnTeamName(),
            scores.getBlackPlayerScore(),
            scores.getWhitePlayerScore(),
            false,
            null
        );
    }

    public ResponseDTO getResponseWhenRequestMove(MoveRequestDTO moveRequestDTO) {
        validateGameStarted();
        String winnerNameIfKingDead = chessGame.currentTurnTeamName();
        chessGame.move(moveRequestDTO);
        chessGame.changeToNextTurn();
        return createResponseDTOWhenRequestMove(winnerNameIfKingDead);
    }

    private ResponseDTO createResponseDTOWhenRequestMove(String winnerNameIfKingDead) {
        Scores scores = chessGame.getScores();
        return new ResponseDTO(
            chessGame.boardCellsStatus(),
            chessGame.currentTurnTeamName(),
            scores.getBlackPlayerScore(),
            scores.getWhitePlayerScore(),
            chessGame.isKingDead(),
            winnerNameIfKingDead
        );
    }

    private void validateGameStarted() {
        if (chessGame == null) {
            throw new IllegalStateException("게임을 먼저 시작해 주세요.");
        }
    }

    public ResponseDTO getScores() {
        validateGameStarted();
        Scores scores = chessGame.getScores();

        return new ResponseDTO(
            chessGame.boardCellsStatus(),
            chessGame.currentTurnTeamName(),
            scores.getBlackPlayerScore(),
            scores.getWhitePlayerScore(),
            false,
            null
        );
    }

    public void endGame() {
        chessGame = null;
    }
}
