package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.ChessPieceDao;
import chess.dao.DBManager;
import chess.domain.ChessGame;
import chess.domain.pieces.Type;
import chess.service.dto.ChessGameDto;
import chess.service.dto.PieceDto;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class NewGameInitializeService implements BoardInitializer {
    private static final int GAME_ID = 1;

    private ChessGameDao chessGameDao;
    private ChessPieceDao chessPieceDao;

    public NewGameInitializeService() {
        DataSource ds = DBManager.createDataSource();
        chessGameDao = new ChessGameDao(ds);
        chessPieceDao = new ChessPieceDao(ds);
    }

    @Override
    public ChessGameDto initialize() throws SQLException {
        ChessGameDto newGameDto = new ChessGameDto();
        ChessGame chessGame = new ChessGame();
        newGameDto.setChessGame(chessGame);
        initializeDB();
        // 데이터베이스에 초기화 배열 저장
        loadBoard(chessGame);
        // 프론트앤드에 보드 정보 주기
        makeJSONBoard(chessGame, newGameDto);
        return newGameDto;
    }

    private void initializeDB() throws SQLException {
        // 이전 게임 데이터 삭제
        chessGameDao.deleteGame();
        chessPieceDao.deleteAll();
        // 새 게임 데이터 추가
        chessGameDao.addGame(GAME_ID);
    }

    private void loadBoard(ChessGame chessGame) {
        chessGame.getBoard().entrySet().stream()
                .forEach(point -> {
                    PieceDto pieceDto = new PieceDto(point.getKey(), point.getValue().getColor(), point.getValue().getType());
                    try {
                        chessPieceDao.addPiece(pieceDto);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void makeJSONBoard(ChessGame chessGame, ChessGameDto newGameDto) {
        Map<String, String> initBoard = new HashMap<>();
        chessGame.getBoard().entrySet().stream()
                .filter(point -> !point.getValue().equalsType(Type.BLANK))
                .forEach(point -> initBoard.put(point.getKey().toString(), point.getValue().toString()));
        newGameDto.setInitWebBoard(initBoard);
    }
}
