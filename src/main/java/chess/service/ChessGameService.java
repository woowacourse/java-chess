package chess.service;

import chess.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.kind.Piece;
import chess.dto.ChessGameDto;
import chess.dto.RequestDto;
import chess.dto.UserDto;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessGameService {
    private final ChessGameDao chessGameDao = new ChessGameDao();
    private ChessGame chessGame;
    private UserDto userDto;

    public Piece getPiece(String coordinate) {
        Point point = Point.of(coordinate);
        return chessGame.getBoard().get(point);
    }

    public Status movePiece(RequestDto requestDto) {
        Point sourcePoint = Point.of(requestDto.getSourcePoint());
        Point targetPoint = Point.of(requestDto.getTargetPoint());
        try {
            chessGame.playTurn(sourcePoint, targetPoint);
            chessGameDao.updateGame(userDto, chessGame);
            if (!chessGame.isProgressing()) {
                return Status.GAME_FINISHED;
            }
            return Status.MOVE_SUCCESS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Status.MOVE_FAILED;
        }
    }

    public void addUser(UserDto userDto) throws SQLException {
        chessGameDao.addUser(userDto);
    }

    public boolean login(UserDto userDto) throws SQLException {
        if (chessGameDao.findUser(userDto) != null) {
            this.userDto = userDto;
            this.chessGame = getGameByUserId(userDto);
            return true;
        }
        return false;
    }

    private ChessGame getGameByUserId(UserDto userDto) throws SQLException {
        ChessGameDto chessGameDto = chessGameDao.findGameByUserId(userDto);
        if (chessGameDto == null) {
            ChessGame chessGame = new ChessGame();
            chessGameDao.createNewGame(userDto, chessGame.getBoard());
            return chessGame;
        }
        return new ChessGame(parseBoard(chessGameDto.getPieceNames()), parseTurn(chessGameDto.getCurrentColor()));
    }

    private Map<Point, Piece> parseBoard(String[] pieceNames) {
        Map<Point, Piece> board = new HashMap<>();
        for (int column = 0; column <= 7; column++) {
            makeRow(pieceNames, board, column);
        }
        return board;
    }

    private void makeRow(String[] pieceNames, Map<Point, Piece> board, int column) {
        for (int row = 7; row >= 0; row--) {
            board.put(Point.of(row, column), PieceType.createByPieceName(pieceNames[(7 - row) + column * 8].charAt(0)));
        }
    }

    private Color parseTurn(String currentColor) {
        return Color.parseColor(currentColor);
    }
}
