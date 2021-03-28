package chess.db.domain.game;

import static chess.beforedb.domain.player.type.TeamColor.BLACK;
import static chess.beforedb.domain.player.type.TeamColor.WHITE;

import chess.beforedb.controller.dto.request.MoveRequestDTO;
import chess.db.dao.ChessGameDAO;
import chess.db.dao.PiecePositionEntities;
import chess.db.domain.board.BoardForDB;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.player.PlayersForDB;
import chess.db.domain.position.MoveRouteForDB;
import chess.db.domain.position.PositionEntitiesCache;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PlayerPiecePositionEntity;
import chess.beforedb.domain.board.setting.BoardCustomSetting;
import chess.beforedb.domain.board.setting.BoardDefaultSetting;
import chess.beforedb.domain.board.setting.BoardSetting;
import chess.beforedb.domain.piece.type.PieceWithColorType;
import chess.beforedb.domain.player.Scores;
import chess.beforedb.domain.player.type.TeamColor;
import java.sql.SQLException;
import java.util.List;

public class ChessGameForDB {
    private final ChessGameDAO chessGameDAO;
    private final PlayersForDB playersForDB;
    private final BoardForDB boardForDB;
    private ChessGameEntity chessGameEntity;
    private TeamColor currentTurnTeamColor = WHITE;

    public ChessGameForDB() {
        playersForDB = new PlayersForDB();
        boardForDB = new BoardForDB();
        chessGameDAO = new ChessGameDAO();
    }

    public void createNew(BoardSetting boardSetting, String title) throws SQLException {
        validate(boardSetting);
        chessGameEntity = chessGameDAO.save(new ChessGameEntity(title));
        playersForDB.createNewPlayers(chessGameEntity);
        setInitialPieces(boardSetting);
    }

    private void validate(BoardSetting boardSetting) {
        if (!(boardSetting instanceof BoardDefaultSetting
            || boardSetting instanceof BoardCustomSetting)) {
            throw new IllegalArgumentException("유효하지 않은 보드 세팅 객체 타입 입니다.");
        }
    }

    private void setInitialPieces(BoardSetting boardSetting) throws SQLException {
        List<PieceWithColorType> piecesSetting = boardSetting.getPiecesSetting();
        for (int index = 0; index < piecesSetting.size(); index++) {
            PositionEntity positionEntity = PositionEntitiesCache.get(index);
            PieceWithColorType pieceWithColorType = piecesSetting.get(index);
            setInitialPiece(pieceWithColorType, positionEntity);
        }
    }

    private void setInitialPiece(PieceWithColorType pieceWithColorType,
        PositionEntity positionEntity) throws SQLException {
        PieceEntity pieceEntity = null;
        if (pieceWithColorType != null) {
            pieceEntity = PieceEntity.of(pieceWithColorType);
            playersForDB.addForNewPlayers(new PiecePositionEntities(pieceEntity, positionEntity));
        }
        boardForDB.setPiece(positionEntity, pieceEntity);
    }

    public void load(Long chessGameId) throws SQLException {
        chessGameEntity = chessGameDAO.findById(chessGameId);
        playersForDB.loadPlayers(chessGameEntity);
    }

    public void move(MoveRequestDTO moveRequestDTO) throws SQLException {
        MoveRouteForDB moveRouteForDB = new MoveRouteForDB(moveRequestDTO);
        boardForDB.validateRoute(moveRouteForDB, currentTurnTeamColor);
        updatePiecesOfPlayers(moveRouteForDB);
        boardForDB.move(moveRouteForDB);
    }

    private void updatePiecesOfPlayers(MoveRouteForDB moveRouteForDB) throws SQLException {
        PieceEntity movingPiece = boardForDB.findPiece(moveRouteForDB.getStartPosition());
        playersForDB.update(movingPiece, moveRouteForDB.getDestination());
        if (boardForDB.isAnyPieceExistsInCell(moveRouteForDB.getDestination())) {
            PieceEntity deadPiece = boardForDB.findPiece(moveRouteForDB.getDestination());
            playersForDB.remove(deadPiece, moveRouteForDB.getDestination());
        }
    }

    public PlayerEntity getPlayerColorOf(TeamColor teamColor) {
        return playersForDB.getPlayerColorOf(teamColor);
    }

    public boolean isKingDead() {
        return boardForDB.isKingDead();
    }

    public List<String> boardCellsStatus() {
        return boardForDB.getStatus();
    }

    public List<PlayerPiecePositionEntity> getAllPiecesPositionsOfAllPlayers() throws SQLException {
        return playersForDB.getAllPiecesPositionsOfChessGame();
    }

    public Scores getScores() {
        return new Scores(
            playersForDB.getPlayerScoreTeamColorOf(BLACK),
            playersForDB.getPlayerScoreTeamColorOf(WHITE));
    }

    public void changeToNextTurn() {
        currentTurnTeamColor = currentTurnTeamColor.oppositeTeamColor();
    }

    public String currentTurnTeamName() {
        return currentTurnTeamColor.getName();
    }

    public String beforeTurnTeamName() {
        TeamColor beforeTurnTeamColor = currentTurnTeamColor.oppositeTeamColor();
        return beforeTurnTeamColor.getName();
    }
}