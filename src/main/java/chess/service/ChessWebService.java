package chess.service;

import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.BoardResponseDTO;
import chess.controller.dto.response.BoardStatusResponseDTO;
import chess.controller.dto.response.ChessGameResponseDTO;
import chess.controller.dto.response.GameStatusResponseDTO;
import chess.controller.dto.response.MoveResponseDTO;
import chess.controller.dto.response.ResponseDTO;
import chess.domain.board.setting.BoardSetting;
import chess.domain.game.ChessGame;
import java.sql.SQLException;
import java.util.List;

public class ChessWebService {
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
    private final ChessGame chessGame;

    public ChessWebService(BoardSetting boardSetting) {
        this.boardSetting = boardSetting;
        chessGame = new ChessGame();
    }

    public Long createNewChessGame(String title) throws SQLException {
        return chessGame.createNew(boardSetting, title);
    }

    public List<ChessGameResponseDTO> getAllRoomsIdAndTitle() throws SQLException {
        return chessGame.getAllGamesIdAndTitle();
    }

    public MoveResponseDTO requestMove(MoveRequestDTO moveRequestDTO) throws SQLException {
        return createMoveResponse(moveRequestDTO);
    }

    private MoveResponseDTO createMoveResponse(MoveRequestDTO moveRequestDTO) throws SQLException {
        try {
            chessGame.move(moveRequestDTO);
        } catch (Exception e) {
            return new MoveResponseDTO(true, e.getMessage());
        }
        chessGame.changeToNextTurn(moveRequestDTO.getGameId());
        return new MoveResponseDTO(false);
    }

    public ResponseDTO getGameStatus(Long gameId) throws SQLException {
        GameStatusResponseDTO gameStatusResponseDTO = chessGame.getGameStatus(gameId);
        BoardStatusResponseDTO boardStatusResponseDTO = chessGame.getBoardStatus(gameId);
        return new ResponseDTO(
            gameStatusResponseDTO,
            boardStatusResponseDTO.isKingDead(),
            getBoardResponseDTO(boardStatusResponseDTO.getCellsStatus()));
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

    public void deleteGame(Long gameId) throws SQLException {
        chessGame.remove(gameId);
    }
}
