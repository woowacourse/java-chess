package chess.domain;

import chess.dao.DbBoardDao;
import chess.dao.DbGameDao;
import chess.dto.ChessBoardDto;
import chess.dto.ChessStatusDto;
import chess.dto.GameInformationDto;
import chess.dto.WebChessStatusDto;
import chess.service.DbService;

public class ChessGame {
    private ChessBoard chessBoard;
    private final DbService dbService;
    private final int gameId;

    private ChessGame(int gameId) {
        this.chessBoard = ChessBoard.initialize();
        this.gameId = gameId;
        this.dbService = DbService.create(new DbGameDao(), new DbBoardDao());
    }

    public static ChessGame create(int gameId) {
        return new ChessGame(gameId);
    }

    public void initialze() {
        dbService.deleteAllData(gameId);
        this.chessBoard = ChessBoard.initialize();
    }

    public void setChessGameForStart() {
        GameInformationDto gameInformationDto = dbService.loadGameInformationDto(gameId);
        if (gameInformationDto == null) {
            dbService.saveInitData(gameId, chessBoard.getTurn(), getChessBoardInformation());
            return;
        }
        initFromDb(gameInformationDto, dbService.getChessBoardInformation(gameId));
    }

    public ChessBoardDto getChessBoardInformation() {
        return ChessBoardDto.of(chessBoard.getMapInformation());
    }

    public boolean isGameEnd() {
        return chessBoard.isKingDie();
    }

    public void move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        chessBoard.move(sourcePosition, targetPosition);
    }

    public void moveAndSave(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        move(sourcePosition, targetPosition);
        dbService.saveDataToDb(gameId, chessBoard.getTurn(), getChessBoardInformation());
    }

    public ChessStatusDto getStatusInformation() {
        return ChessStatusDto.of(chessBoard);
    }

    public WebChessStatusDto getStatusInformationForWeb() {
        return WebChessStatusDto.of(chessBoard);
    }

    public int getGameId() {
        return gameId;
    }

    private void initFromDb(GameInformationDto gameInformationDto, ChessBoardDto chessBoardDto) {
        chessBoard.initFromDb(gameInformationDto, chessBoardDto);
    }
}
