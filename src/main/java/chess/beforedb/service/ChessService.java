package chess.beforedb.service;

import chess.beforedb.domain.board.setting.BoardSetting;
import chess.db.controller.dto.request.MoveRequestDTOForDB;
import chess.db.controller.dto.response.BoardResponseDTOForDB;
import chess.db.controller.dto.response.BoardStatusResponseDTOForDB;
import chess.db.controller.dto.response.MoveResponseDTOForDB;
import chess.db.controller.dto.response.ResponseDTOForDB;
import chess.db.domain.game.ChessGameForDB;
import chess.db.domain.game.GameStatusResponseDTO;
import java.sql.SQLException;
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
    private Long gameId;

    private final BoardSetting boardSetting;

    private ChessGameForDB chessGameForDB;

    public ChessService(BoardSetting boardSetting) {
        this.boardSetting = boardSetting;
    }

    public void start() throws SQLException {
        chessGameForDB = new ChessGameForDB();
        gameId = chessGameForDB.createNew(boardSetting, "콘솔용 게임 제목");
    }

    public MoveResponseDTOForDB requestMove(MoveRequestDTOForDB moveRequestDTO) throws Exception {
        validateGameStarted();
        moveRequestDTO.setGameId(gameId);
        return createMoveResponse(moveRequestDTO);
    }

    private MoveResponseDTOForDB createMoveResponse(MoveRequestDTOForDB moveRequestDTO)
        throws SQLException {

        chessGameForDB.move(moveRequestDTO);
        chessGameForDB.changeToNextTurn(gameId);
        return new MoveResponseDTOForDB(false);
    }

    private void validateGameStarted() {
        if (chessGameForDB == null) {
            throw new IllegalStateException("게임이 아직 시작되지 않았습니다.");
        }
    }

    public ResponseDTOForDB getCurrentBoard() throws SQLException {
        GameStatusResponseDTO gameStatusResponseDTO = chessGameForDB.getGameStatus(gameId);
        BoardStatusResponseDTOForDB boardStatusResponseDTOForDB
            = chessGameForDB.getBoardStatus(gameId);
        return new ResponseDTOForDB(
            gameStatusResponseDTO,
            boardStatusResponseDTOForDB.isKingDead(),
            getBoardResponseDTO(boardStatusResponseDTOForDB.getCellsStatus()));
    }

    private BoardResponseDTOForDB getBoardResponseDTO(List<String> cellsStatus) {
        return new BoardResponseDTOForDB(
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
        if (chessGameForDB == null || gameId == null) {
            return;
        }
        chessGameForDB.remove(gameId);
        chessGameForDB = null;
        gameId = null;
    }
}
