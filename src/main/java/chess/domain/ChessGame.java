package chess.domain;

import chess.dao.DbBoardDao;
import chess.dao.DbGameDao;
import chess.domain.piece.ChessPiece;
import chess.dto.ChessBoardDto;
import chess.dto.ChessStatusDto;
import chess.dto.GameInformationDto;
import chess.dto.WebChessStatusDto;
import chess.service.DbService;
import java.util.Map;

public class ChessGame {
    private ChessBoard chessBoard;
    private final DbService dbService;
    private final int gameId;

    private ChessGame(int gameId) {
        this.gameId = gameId;
        this.dbService = DbService.create(new DbGameDao(), new DbBoardDao());
    }

    public static ChessGame create(int gameId) {
        return new ChessGame(gameId);
    }

    public void initialize(Team turn, Map<ChessBoardPosition, ChessPiece> boardData) {
        this.chessBoard = ChessBoard.initialize(turn, boardData);
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
}
