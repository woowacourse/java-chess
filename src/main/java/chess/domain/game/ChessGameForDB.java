package chess.domain.game;

import chess.controller.dto.request.MoveRequestDTOForDB;
import chess.controller.dto.response.BoardStatusResponseDTOForDB;
import chess.controller.dto.response.ChessGameResponseDTO;
import chess.controller.dto.response.GameStatusResponseDTO;
import chess.dao.ChessGameDAO;
import chess.dao.entity.GameStatusEntity;
import chess.domain.board.BoardForDB;
import chess.domain.board.move.MoveRequestForDB;
import chess.domain.board.setting.BoardCustomSetting;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.domain.player.score.ScoresEntity;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameForDB {
    private final ChessGameDAO chessGameDAO;
    private final BoardForDB boardForDB;

    public ChessGameForDB() {
        boardForDB = new BoardForDB();
        chessGameDAO = new ChessGameDAO();
    }

    public Long createNew(BoardSetting boardSetting, String title) throws SQLException {
        validate(boardSetting);
        ChessGameEntity chessGameEntity = chessGameDAO.save(new ChessGameEntity(title));
        boardForDB.createAndSaveNewPlayersAndPiecesPositionsOfGame(
            chessGameEntity.getId(), boardSetting);
        return chessGameEntity.getId();
    }

    private void validate(BoardSetting boardSetting) {
        if (!(boardSetting instanceof BoardDefaultSetting
            || boardSetting instanceof BoardCustomSetting)) {
            throw new IllegalArgumentException("유효하지 않은 보드 세팅 객체 타입 입니다.");
        }
    }

    public List<ChessGameResponseDTO> getAllGamesIdAndTitle() throws SQLException {
        List<ChessGameResponseDTO> chessGameResponseDTOs = new ArrayList<>();
        for (ChessGameEntity chessGameEntity : chessGameDAO.findAll()) {
            chessGameResponseDTOs.add(
                new ChessGameResponseDTO(chessGameEntity.getId(), chessGameEntity.getTitle()));
        }
        return chessGameResponseDTOs;
    }

    public void move(MoveRequestDTOForDB moveRequestDTO) throws SQLException {
        ChessGameEntity chessGameEntity = chessGameDAO.findById(moveRequestDTO.getGameId());
        MoveRequestForDB moveRequestForDB
            = new MoveRequestForDB(chessGameEntity.getCurrentTurnTeamColor(), moveRequestDTO);
        boardForDB.validateRoute(chessGameEntity.getId(), moveRequestForDB);
        boardForDB.move(chessGameEntity.getId(), moveRequestForDB);
    }

    public BoardStatusResponseDTOForDB getBoardStatus(Long gameId) throws SQLException {
        return boardForDB.getStatus(gameId);
    }

    public GameStatusResponseDTO getGameStatus(Long gameId) throws SQLException {
        GameStatusEntity gameStatusEntity = chessGameDAO.findStatusByGameId(gameId);
        ScoresEntity scores = boardForDB.getScores(gameId);
        return new GameStatusResponseDTO(
            gameId,
            gameStatusEntity.getTitle(),
            gameStatusEntity.getCurrentTurnTeamColor(),
            scores.getWhitePlayerScore(),
            scores.getBlackPlayerScore());
    }

    public void changeToNextTurn(Long gameId) throws SQLException {
        ChessGameEntity chessGameEntity = chessGameDAO.findById(gameId);
        TeamColor currentTurnTeamColor = chessGameEntity.getCurrentTurnTeamColor();
        chessGameEntity.setCurrentTurnTeamColor(currentTurnTeamColor.oppositeTeamColor());
        chessGameDAO.updateCurrentTurnTeamColor(chessGameEntity);
    }

    public void remove(Long gameId) throws SQLException {
        boardForDB.removeAllPlayersAndPiecesPositionsOfGame(gameId);
        chessGameDAO.remove(gameId);
    }
}