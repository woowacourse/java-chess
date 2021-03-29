package chess.db.service;

import chess.beforedb.controller.dto.request.MoveRequestDTO;
import chess.beforedb.controller.dto.response.ResponseDTO;
import chess.beforedb.controller.dto.response.MoveResponse;
import chess.beforedb.domain.board.setting.BoardSetting;
import chess.db.controller.dto.request.MoveRequestDTOForDB;
import chess.db.controller.dto.response.BoardResponseDTOForDB;
import chess.db.controller.dto.response.BoardStatusResponseDTOForDB;
import chess.db.controller.dto.response.MoveResponseDTOForDB;
import chess.db.controller.dto.response.ResponseDTOForDB;
import chess.db.domain.game.ChessGameForDB;
import chess.db.domain.game.ChessGameResponseDTO;
import chess.db.domain.game.GameStatusResponseDTO;
import java.sql.SQLException;
import java.util.List;

public class ChessServiceForDB {
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
    private final ChessGameForDB chessGameForDB;

    public ChessServiceForDB(BoardSetting boardSetting) {
        this.boardSetting = boardSetting;
        chessGameForDB = new ChessGameForDB();
    }

    public Long createNewChessGame(String title) throws SQLException {
        return chessGameForDB.createNew(boardSetting, title);
    }

    public List<ChessGameResponseDTO> getAllRoomsIdAndTitle() throws SQLException {
        return chessGameForDB.getAllGamesIdAndTitle();
    }

    public MoveResponseDTOForDB requestMove(MoveRequestDTOForDB moveRequestDTO) throws SQLException {
        return createMoveResponse(moveRequestDTO);
    }

    private MoveResponseDTOForDB createMoveResponse(MoveRequestDTOForDB moveRequestDTO) throws SQLException {
        try {
            chessGameForDB.move(moveRequestDTO);
        } catch (Exception e) {
            return new MoveResponseDTOForDB(true, e.getMessage());
        }
        chessGameForDB.changeToNextTurn(moveRequestDTO.getGameId());
        return new MoveResponseDTOForDB(false);
    }

    public ResponseDTOForDB getGameStatus(Long gameId) throws SQLException {
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

    public void endGame(Long gameId) throws SQLException {
        chessGameForDB.end(gameId);
    }
}
