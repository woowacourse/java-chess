package chess.domain;

import chess.domain.piece.ChessPiece;
import chess.dto.ChessBoardDto;
import chess.dto.ChessStatusDto;
import chess.dto.GameInformationDto;
import chess.dto.WebChessStatusDto;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private ChessBoard chessBoard;
    private int gameId;

    private ChessGame(int gameId) {
        this.chessBoard = ChessBoard.initialize();
        this.gameId = gameId;
    }

    public static ChessGame create(int gameId) {
        return new ChessGame(gameId);
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

    public ChessStatusDto getStatusInformation() {
        return ChessStatusDto.of(chessBoard);
    }

    public WebChessStatusDto getStatusInformationForWeb() {
        return WebChessStatusDto.of(chessBoard);
    }

    public int getGameId() {
        return gameId;
    }

    public void initialze() {
        this.chessBoard = ChessBoard.initialize();
    }

    public Team getTurn() {
        return chessBoard.getTurn();
    }

    public void initFromDb(GameInformationDto gameInformationDto, ChessBoardDto chessBoardDto) {
        gameId = gameInformationDto.getId();
        chessBoard.initFromDb(gameInformationDto, chessBoardDto);

    }
}
