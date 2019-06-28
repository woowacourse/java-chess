package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.ChessPieceDao;
import chess.dao.DBManager;
import chess.domain.ChessGame;
import chess.domain.Point;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.PieceFactory;
import chess.domain.pieces.PointFactory;
import chess.service.dto.ChessGameDto;
import chess.service.dto.PieceDto;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ContinueGameInitializeService implements BoardInitializer {
    private static final int START_PIECE_ID = 1;
    private static final int END_PIECE_ID = 64;
    private static final int START_FIRST_CHAR = 0;
    private static final int END_FIRST_CHAR = 1;
    private static final int START_SECOND_CHAR = 1;
    private static final int END_SECOND_CHAR = 2;
    private static final int GAME_ID = 1;

    private ChessGameDao chessGameDao;
    private ChessPieceDao chessPieceDao;

    public ContinueGameInitializeService() {
        DataSource ds = DBManager.createDataSource();
        chessGameDao = new ChessGameDao(ds);
        chessPieceDao = new ChessPieceDao(ds);
    }

    @Override
    public ChessGameDto initialize() throws SQLException {
        ChessGameDto chessGameDto = new ChessGameDto();
        Color currentTurn = chessGameDao.findTurn(GAME_ID).equals("WHITE") ? Color.WHITE : Color.BLACK;
        BoardDto boardDto = new BoardDto();
        setBoardDto(boardDto);
        ChessGame chessGame = new ChessGame(currentTurn, boardDto.getPreviousBoard());

        chessGameDto.setCurrentOfTurn(currentTurn);
        chessGameDto.setInitWebBoard(boardDto.getWebBoard());
        chessGameDto.setChessGame(chessGame);

        return chessGameDto;
    }

    private void setBoardDto(BoardDto boardDto) throws SQLException {
        Map<String, String> initBoard = new HashMap<>();
        Map<Point, Piece> gameBoard = new HashMap<>();
        for (int i = START_PIECE_ID; i <= END_PIECE_ID; ++i) {
            PieceDto piece = chessPieceDao.findPieceById(String.valueOf(i));
            String color = piece.getColor().substring(START_FIRST_CHAR, END_FIRST_CHAR).toLowerCase();
            String type = piece.getType().equals("KNIGHT")
                    ? piece.getType().substring(START_SECOND_CHAR, END_SECOND_CHAR)
                    : piece.getType().substring(START_FIRST_CHAR, END_FIRST_CHAR);
            gameBoard.put(PointFactory.of(piece.getPoint()), PieceFactory.of(color + type));
            if (piece.getType().equals("BLANK")) {
                continue;
            }
            initBoard.put(piece.getPoint(), color + type);
        }
        boardDto.setPreviousBoard(gameBoard);
        boardDto.setWebBoard(initBoard);
    }

    public class BoardDto {
        private Map<String, String> webBoard;
        private Map<Point, Piece> previousBoard;

        public Map<String, String> getWebBoard() {
            return webBoard;
        }

        public void setWebBoard(Map<String, String> webBoard) {
            this.webBoard = webBoard;
        }

        public Map<Point, Piece> getPreviousBoard() {
            return previousBoard;
        }

        public void setPreviousBoard(Map<Point, Piece> previousBoard) {
            this.previousBoard = previousBoard;
        }
    }
}
