package chess.service;

import chess.controller.dto.request.CommandRequestDTO;
import chess.controller.dto.response.BoardStatusResponseDTO;
import chess.controller.dto.response.ChessGameResponseDTO;
import chess.controller.dto.response.MoveResultResponseDTO;
import chess.controller.dto.response.ScoresResponseDTO;
import chess.domain.game.ChessGame;

public class ChessService {
    private final ChessGame chessGame;

    public ChessService(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public ChessGameResponseDTO startChessGameAndGetInitialBoardStatus() {
        chessGame.start();
        BoardStatusResponseDTO boardStatusResponseDTO = chessGame.boardStatus();
        return new ChessGameResponseDTO(boardStatusResponseDTO);
    }

    public ChessGameResponseDTO movePieceAndGetResult(CommandRequestDTO commandRequestDTO) {
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

    private BoardStatusResponseDTO movePieceAndGetBoardStatusResponseDTO(
        CommandRequestDTO commandRequestDTO) {
        chessGame.move(commandRequestDTO);
        chessGame.changeCurrentTurnTeamColorToOpposite();
        return chessGame.boardStatus();
    }

    public ChessGameResponseDTO getScores() {
        ScoresResponseDTO scoresResponseDTO = chessGame.getScores();
        return new ChessGameResponseDTO(scoresResponseDTO);
    }
}
