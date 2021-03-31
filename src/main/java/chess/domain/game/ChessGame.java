package chess.domain.game;

import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.BoardStatusResponseDTO;
import chess.controller.dto.response.ChessGameResponseDTO;
import chess.controller.dto.response.GameStatusResponseDTO;
import chess.dao.entity.ChessGameEntity;
import chess.dao.entity.GameStatusEntity;
import chess.dao.game.ChessGameDAO;
import chess.dao.game.ChessGameRepository;
import chess.domain.board.Board;
import chess.domain.board.move.MoveRequest;
import chess.domain.board.setting.BoardCustomSetting;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.domain.player.score.Scores;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private final ChessGameRepository chessGameRepository;
    private final Board board;

    public ChessGame() {
        board = new Board();
        chessGameRepository = new ChessGameDAO();
    }

    public Long createNew(BoardSetting boardSetting, String title) throws SQLException {
        validate(boardSetting);
        ChessGameEntity chessGameEntity = chessGameRepository.save(new ChessGameEntity(title));
        board.createAndSaveNewPlayersAndPiecesPositionsOfGame(chessGameEntity.getId(), boardSetting);
        return chessGameEntity.getId();
    }

    private void validate(BoardSetting boardSetting) {
        if (!(boardSetting instanceof BoardDefaultSetting || boardSetting instanceof BoardCustomSetting)) {
            throw new IllegalArgumentException("유효하지 않은 보드 세팅 객체 타입 입니다.");
        }
    }

    public List<ChessGameResponseDTO> getAllGamesIdAndTitle() throws SQLException {
        List<ChessGameResponseDTO> chessGameResponseDTOs = new ArrayList<>();
        for (ChessGameEntity chessGameEntity : chessGameRepository.findAll()) {
            chessGameResponseDTOs.add(new ChessGameResponseDTO(chessGameEntity.getId(), chessGameEntity.getTitle()));
        }
        return chessGameResponseDTOs;
    }

    public void move(MoveRequestDTO moveRequestDTO) throws SQLException {
        ChessGameEntity chessGameEntity = chessGameRepository.findById(moveRequestDTO.getGameId());
        MoveRequest moveRequest = new MoveRequest(chessGameEntity.getCurrentTurnTeamColor(), moveRequestDTO);
        board.validateRoute(chessGameEntity.getId(), moveRequest);
        board.move(chessGameEntity.getId(), moveRequest);
    }

    public BoardStatusResponseDTO getBoardStatus(Long gameId) throws SQLException {
        return board.getStatus(gameId);
    }

    public GameStatusResponseDTO getGameStatus(Long gameId) throws SQLException {
        GameStatusEntity gameStatusEntity = chessGameRepository.findStatusByGameId(gameId);
        Scores scores = board.getScores(gameId);
        return new GameStatusResponseDTO(
            gameId,
            gameStatusEntity.getTitle(),
            gameStatusEntity.getCurrentTurnTeamColor(),
            scores.getWhitePlayerScore(),
            scores.getBlackPlayerScore());
    }

    public void changeToNextTurn(Long gameId) throws SQLException {
        ChessGameEntity chessGameEntity = chessGameRepository.findById(gameId);
        TeamColor currentTurnTeamColor = chessGameEntity.getCurrentTurnTeamColor();
        chessGameEntity.setCurrentTurnTeamColor(currentTurnTeamColor.oppositeTeamColor());
        chessGameRepository.updateCurrentTurnTeamColor(chessGameEntity);
    }

    public void remove(Long gameId) throws SQLException {
        board.removeAllPlayersAndPiecesPositionsOfGame(gameId);
        chessGameRepository.remove(gameId);
    }
}