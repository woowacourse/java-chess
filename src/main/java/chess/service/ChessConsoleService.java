package chess.service;

import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.BoardResponseDTO;
import chess.controller.dto.response.BoardStatusResponseDTOForDB;
import chess.controller.dto.response.GameStatusResponseDTO;
import chess.controller.dto.response.MoveResponseDTO;
import chess.controller.dto.response.ResponseDTO;
import chess.domain.board.setting.BoardSetting;
import chess.domain.game.ChessGame;
import java.sql.SQLException;
import java.util.List;

public class ChessConsoleService {
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
    private Long gameId;

    private final BoardSetting boardSetting;

    private ChessGame chessGame;

    public ChessConsoleService(BoardSetting boardSetting) {
        this.boardSetting = boardSetting;
    }

    public void start() throws SQLException {
        chessGame = new ChessGame();
        gameId = chessGame.createNew(boardSetting, "콘솔용 게임 제목");
    }

    public MoveResponseDTO requestMove(MoveRequestDTO moveRequestDTO) throws Exception {
        validateGameStarted();
        moveRequestDTO.setGameId(gameId);
        return createMoveResponse(moveRequestDTO);
    }

    private MoveResponseDTO createMoveResponse(MoveRequestDTO moveRequestDTO)
        throws SQLException {

        chessGame.move(moveRequestDTO);
        chessGame.changeToNextTurn(gameId);
        return new MoveResponseDTO(false);
    }

    private void validateGameStarted() {
        if (chessGame == null) {
            throw new IllegalStateException("게임이 아직 시작되지 않았습니다.");
        }
    }

    public ResponseDTO getCurrentBoard() throws SQLException {
        GameStatusResponseDTO gameStatusResponseDTO = chessGame.getGameStatus(gameId);
        BoardStatusResponseDTOForDB boardStatusResponseDTOForDB
            = chessGame.getBoardStatus(gameId);
        return new ResponseDTO(
            gameStatusResponseDTO,
            boardStatusResponseDTOForDB.isKingDead(),
            getBoardResponseDTO(boardStatusResponseDTOForDB.getCellsStatus()));
    }

    private BoardResponseDTO getBoardResponseDTO(List<String> cellsStatus) {
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

    public void endGame() throws Exception {
        if (chessGame == null || gameId == null) {
            return;
        }
        chessGame.remove(gameId);
        chessGame = null;
        gameId = null;
    }
}
