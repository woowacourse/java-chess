package chess.db.domain.game;

import chess.beforedb.controller.dto.request.MoveRequestDTO;
import chess.beforedb.domain.board.setting.BoardCustomSetting;
import chess.beforedb.domain.board.setting.BoardDefaultSetting;
import chess.beforedb.domain.board.setting.BoardSetting;
import chess.beforedb.domain.piece.type.PieceWithColorType;
import chess.beforedb.domain.player.type.TeamColor;
import chess.db.dao.ChessGameDAO;
import chess.db.dao.PiecePosition;
import chess.db.domain.board.BoardForDB;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.player.PlayersForDB;
import chess.db.domain.position.MoveRequestForDB;
import chess.db.domain.position.MoveRouteForDB;
import chess.db.domain.position.PositionEntitiesCache;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerPiecePosition;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameForDB {
    private final ChessGameDAO chessGameDAO;
    private final PlayersForDB playersForDB;
    private final BoardForDB boardForDB;
    private ChessGameEntity chessGameEntity;

    public ChessGameForDB() {
        playersForDB = new PlayersForDB();
        boardForDB = new BoardForDB();
        chessGameDAO = new ChessGameDAO();
    }

    public void createNew(BoardSetting boardSetting, String title) throws SQLException {
        validate(boardSetting);
        chessGameEntity = chessGameDAO.save(new ChessGameEntity(title));
        playersForDB.createNewPlayers(chessGameEntity);
        saveInitialPieces(boardSetting, chessGameEntity.getId());
    }

    private void validate(BoardSetting boardSetting) {
        if (!(boardSetting instanceof BoardDefaultSetting
            || boardSetting instanceof BoardCustomSetting)) {
            throw new IllegalArgumentException("유효하지 않은 보드 세팅 객체 타입 입니다.");
        }
    }

    private void saveInitialPieces(BoardSetting boardSetting, Long gameId) throws SQLException {
        List<PieceWithColorType> piecesSetting = boardSetting.getPiecesSetting();
        for (int index = 0; index < piecesSetting.size(); index++) {
            PositionEntity positionEntity = PositionEntitiesCache.get(index);
            PieceWithColorType pieceWithColorType = piecesSetting.get(index);
            saveInitialPiece(pieceWithColorType, positionEntity, gameId);
        }
    }

    private void saveInitialPiece(PieceWithColorType pieceWithColorType,
        PositionEntity positionEntity, Long gameId) throws SQLException {
        if (pieceWithColorType != null) {
            PieceEntity pieceEntity = PieceEntity.of(pieceWithColorType);
            playersForDB.saveInitialPiecesPositions(
                new PiecePosition(pieceEntity, positionEntity), gameId);
        }
    }

    public List<PlayerPiecePosition> getAllPiecesPositionsOfPlayers() throws SQLException {
        return playersForDB.getAllPiecesPositionsGameOf();
    }

    public List<ChessGameResponseDTO> getAllGamesIdAndTitle() throws SQLException {
        List<ChessGameResponseDTO> chessGameResponseDTOs = new ArrayList<>();
        for (ChessGameEntity chessGameEntity : chessGameDAO.findAll()) {
            chessGameResponseDTOs.add(
                new ChessGameResponseDTO(chessGameEntity.getId(), chessGameEntity.getTitle()));
        }
        return chessGameResponseDTOs;
    }

    public void move(Long gameId, MoveRequestDTO moveRequestDTO) throws SQLException {
        ChessGameEntity chessGameEntity = chessGameDAO.findById(gameId);
        MoveRequestForDB moveRequestForDB
            = new MoveRequestForDB(chessGameEntity.getCurrentTurnTeamColor(), moveRequestDTO);
        boardForDB.validateRoute(chessGameEntity.getId(), moveRequestForDB);
        //updatePiecesOfPlayers(moveRequestForDB);
        //boardForDB.move(moveRequestForDB);
    }

    private void updatePiecesOfPlayers(MoveRouteForDB moveRouteForDB) throws SQLException {
        PieceEntity movingPiece = boardForDB.findPiece(moveRouteForDB.getStartPosition());
        playersForDB.updatePiecePosition(movingPiece, moveRouteForDB.getDestination());
//        if (boardForDB.isAnyPieceExistsInCell(moveRouteForDB.getDestination())) {
//            PieceEntity deadPiece = boardForDB.findPiece(moveRouteForDB.getDestination());
//            playersForDB.removePiece(deadPiece, moveRouteForDB.getDestination());
//        }
    }

    public boolean isKingDead() {
        return boardForDB.isKingDead();
    }

    public List<String> boardCellsStatus(Long gameId) throws SQLException {
        return boardForDB.getStatus(gameId);
    }

    public GameStatusResponseDTO getGameStatus(Long gameId) throws SQLException {
        GameStatusEntity gameStatusEntity = chessGameDAO.findStatusByGameId(gameId);
        return new GameStatusResponseDTO(
            gameStatusEntity.getTitle(),
            gameStatusEntity.getCurrentTurnTeamColor(),
            gameStatusEntity.getWhitePlayerScore(),
            gameStatusEntity.getBlackPlayerScore()
        );
    }

    public void changeToNextTurn() throws SQLException {
        TeamColor currentTurnTeamColor = chessGameEntity.getCurrentTurnTeamColor();
        chessGameEntity.setCurrentTurnTeamColor(currentTurnTeamColor.oppositeTeamColor());
        chessGameDAO.updateCurrentTurnTeamColor(chessGameEntity);
    }

    public String currentTurnTeamName() {
        return chessGameEntity.getCurrentTurnTeamColorName();
    }

    public String beforeTurnTeamName() {
        return chessGameEntity.getOppositeTeamColorName();
    }

    public void end() throws SQLException {
        playersForDB.removePlayersOfChessGame(chessGameEntity);
        chessGameDAO.remove(chessGameEntity);
    }
}