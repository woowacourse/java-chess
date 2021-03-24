package chess.service;

import chess.controller.dto.request.CommandRequestDTO;
import chess.controller.dto.response.BoardStatusResponseDTO;
import chess.controller.dto.response.ChessGameResponseDTO;
import chess.controller.dto.response.MoveResultResponseDTO;
import chess.controller.dto.response.ScoresResponseDTO;
import chess.domain.board.setting.BoardSetting;
import chess.domain.game.ChessGame;

public class ChessService {
    private ChessGame chessGame;
    private final BoardSetting boardSetting;

    public ChessService(BoardSetting boardSetting) {
        this.boardSetting = boardSetting;
    }

    public ChessGameResponseDTO startChessGameAndGetInitialBoardStatus() {
        chessGame = new ChessGame(boardSetting);
        BoardStatusResponseDTO boardStatusResponseDTO = chessGame.boardStatus();
        return new ChessGameResponseDTO(boardStatusResponseDTO);
    }

    public ChessGameResponseDTO movePieceAndGetResult(CommandRequestDTO commandRequestDTO) {
        validateChessGameStarted();
        BoardStatusResponseDTO boardStatusResponseDTO
            = movePieceAndGetBoardStatusResponseDTO(commandRequestDTO);
        boolean isKingDead = chessGame.isKingDead();
        String winnerTeamColorName = null;
        if (isKingDead) {
            winnerTeamColorName = chessGame.winnerTeamColorName();
        }
        MoveResultResponseDTO moveResultResponseDTO = new MoveResultResponseDTO(
            boardStatusResponseDTO, isKingDead, winnerTeamColorName);
        return new ChessGameResponseDTO(moveResultResponseDTO);
    }

    private void validateChessGameStarted() {
        if (chessGame == null) {
            throw new IllegalStateException("게임을 먼저 시작해 주세요.");
        }
    }

    private BoardStatusResponseDTO movePieceAndGetBoardStatusResponseDTO(
        CommandRequestDTO commandRequestDTO) {
        chessGame.move(commandRequestDTO);
        chessGame.changeCurrentTurnTeamColorToOpposite();
        return chessGame.boardStatus();
    }

    public ChessGameResponseDTO getScores() {
        validateChessGameStarted();
        ScoresResponseDTO scoresResponseDTO = chessGame.getScores();
        return new ChessGameResponseDTO(scoresResponseDTO);
    }
}
