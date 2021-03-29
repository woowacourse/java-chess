package chess.db.service;

import chess.beforedb.controller.dto.request.MoveRequestDTO;
import chess.beforedb.controller.dto.response.BoardResponseDTO;
import chess.beforedb.controller.dto.response.ResponseDTO;
import chess.beforedb.controller.web.MoveResponse;
import chess.beforedb.domain.board.setting.BoardSetting;
import chess.beforedb.domain.player.Scores;
import chess.db.domain.game.ChessGameForDB;
import chess.db.domain.game.ChessGameResponseDTO;
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

    public void createNewChessGame(String title) throws SQLException {
        chessGameForDB.createNew(boardSetting, title);
    }

    public List<ChessGameResponseDTO> getAllRoomsIdAndTitle() throws SQLException {
        return chessGameForDB.getAllGamesIdAndTitle();
    }

    public MoveResponse requestMove(MoveRequestDTO moveRequestDTO) throws SQLException {
        return createMoveResponse(moveRequestDTO);
    }

    private MoveResponse createMoveResponse(MoveRequestDTO moveRequestDTO) throws SQLException {
        try {
            //chessGameForDB.move(moveRequestDTO);
        } catch (Exception e) {
            return new MoveResponse(true, e.getMessage());
        }
        chessGameForDB.changeToNextTurn();
        return new MoveResponse(false);
    }

    public ResponseDTO getBoard(Long roomId) throws SQLException {
        //Scores scores = chessGameForDB.getGameStatus();
//        return new ResponseDTO(
//            getBoardResponseDTO(roomId),
//            chessGameForDB.currentTurnTeamName(),
//            scores.getBlackPlayerScore(),
//            scores.getWhitePlayerScore(),
//            chessGameForDB.isKingDead(),
//            chessGameForDB.beforeTurnTeamName());
        return null;
    }

    private BoardResponseDTO getBoardResponseDTO(Long gameId) throws SQLException {
        List<String> cellsStatus = chessGameForDB.boardCellsStatus(gameId);
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

    public void endGame() throws SQLException {
        // chessGameForDB = null;
        chessGameForDB.end();
    }
}
