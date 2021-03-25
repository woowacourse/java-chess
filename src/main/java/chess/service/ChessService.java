package chess.service;

import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.BoardResponseDTO;
import chess.controller.dto.response.ResponseDTO;
import chess.domain.board.setting.BoardSetting;
import chess.domain.game.ChessGame;
import chess.domain.player.Scores;
import java.util.List;

public class ChessService {
    private static final int FILES_SIZE_IN_ONE_RANK = 8;
    private static final int BOARD_ALL_CELLS_SIZE = 64;
    private static final int RANK1_FIRST_INDEX = 0;
    private static final int RANK2_FIRST_INDEX = RANK1_FIRST_INDEX + FILES_SIZE_IN_ONE_RANK;
    private static final int RANK3_FIRST_INDEX = RANK2_FIRST_INDEX + FILES_SIZE_IN_ONE_RANK;
    private static final int RANK4_FIRST_INDEX = RANK3_FIRST_INDEX + FILES_SIZE_IN_ONE_RANK;
    private static final int RANK5_FIRST_INDEX = RANK4_FIRST_INDEX + FILES_SIZE_IN_ONE_RANK;
    private static final int RANK6_FIRST_INDEX = RANK5_FIRST_INDEX + FILES_SIZE_IN_ONE_RANK;
    private static final int RANK7_FIRST_INDEX = RANK6_FIRST_INDEX + FILES_SIZE_IN_ONE_RANK;
    private static final int RANK8_FIRST_INDEX = RANK7_FIRST_INDEX + FILES_SIZE_IN_ONE_RANK;

    private final BoardSetting boardSetting;
    private ChessGame chessGame;

    public ChessService(BoardSetting boardSetting) {
        this.boardSetting = boardSetting;
    }

    public ResponseDTO getResponseWhenRequestStart() {
        chessGame = new ChessGame(boardSetting);
        Scores scores = chessGame.getScores();
        return new ResponseDTO(
            getBoardResponseDTO(),
            chessGame.currentTurnTeamName(),
            scores.getBlackPlayerScore(),
            scores.getWhitePlayerScore(),
            false,
            null
        );
    }

    private BoardResponseDTO getBoardResponseDTO() {
        List<String> cellsStatus = chessGame.boardCellsStatus();
        return new BoardResponseDTO(
            cellsStatus.subList(RANK1_FIRST_INDEX, RANK2_FIRST_INDEX),
            cellsStatus.subList(RANK2_FIRST_INDEX, RANK3_FIRST_INDEX),
            cellsStatus.subList(RANK3_FIRST_INDEX, RANK4_FIRST_INDEX),
            cellsStatus.subList(RANK4_FIRST_INDEX, RANK5_FIRST_INDEX),
            cellsStatus.subList(RANK5_FIRST_INDEX, RANK6_FIRST_INDEX),
            cellsStatus.subList(RANK6_FIRST_INDEX, RANK7_FIRST_INDEX),
            cellsStatus.subList(RANK7_FIRST_INDEX, RANK8_FIRST_INDEX),
            cellsStatus.subList(RANK8_FIRST_INDEX, BOARD_ALL_CELLS_SIZE));
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
            getBoardResponseDTO(),
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

    public void endGame() {
        chessGame = null;
    }
}
