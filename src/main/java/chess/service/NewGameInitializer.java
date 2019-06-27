package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.ChessPieceDao;
import chess.dao.DBManager;
import chess.domain.ChessGame;
import chess.domain.pieces.Type;
import chess.service.dto.ChessBoardDto;
import chess.service.dto.PieceDto;
import spark.Request;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class NewGameInitializer implements BoardInitializer {
    private static final int START_PIECE_ID = 1;
    private static final int END_PIECE_ID = 64;
    private static final int GAME_ID = 1;

    private ChessGameDao chessGameDao;
    private ChessPieceDao chessPieceDao;
    private ChessBoardDto chessBoardDto;

    public NewGameInitializer() {
        DataSource ds = DBManager.createDataSource();
        chessGameDao = new ChessGameDao(ds);
        chessPieceDao = new ChessPieceDao(ds);
        chessBoardDto = new ChessBoardDto();
    }

    @Override
    public ChessBoardDto initialize(Request request) throws SQLException {
        ChessGame chessGame = new ChessGame();
        chessBoardDto.setGameBoard(chessGame.getBoard());
        initializeDB();
        // 데이터베이스에 초기화 배열 저장
        loadBoard();
        // 프론트앤드에 보드 정보 주기
        makeJSONBoard();
        request.session().attribute("chessGame", chessGame);
        return chessBoardDto;
    }

    private void loadBoard() {
        chessBoardDto.getGameBoard().entrySet().stream()
                .forEach(point -> {
                    PieceDto pieceDto = new PieceDto(point.getKey(), point.getValue().getColor(), point.getValue().getType());
                    try {
                        chessPieceDao.addPiece(pieceDto);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void initializeDB() throws SQLException {
        // 이전 게임 데이터 삭제
        chessGameDao.deleteGame();
        for (int i = START_PIECE_ID; i <= END_PIECE_ID; ++i) {
            chessPieceDao.deletePieceById(String.valueOf(i));
        }
        // 새 게임 데이터 추가
        chessGameDao.addGame(GAME_ID);
    }

    private void makeJSONBoard() {
        Map<String, String> initBoard = new HashMap<>();
        chessBoardDto.getGameBoard().entrySet().stream()
                .filter(point -> !point.getValue().equalsType(Type.BLANK))
                .forEach(point -> initBoard.put(point.getKey().toString(), point.getValue().toString()));
        chessBoardDto.setInitWebBoard(initBoard);
    }
}
