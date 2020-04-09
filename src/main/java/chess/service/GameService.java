package chess.service;

import chess.dao.BoardDAO;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Row;
import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.game.GameStatus;
import chess.domain.game.Team;
import chess.domain.move.Coordinate;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.dto.ChessPieceDTO;
import chess.dto.GameStatusDTO;
import chess.generator.ChessPieceGenerator;
import chess.generator.JSONGenerator;

import java.sql.SQLException;
import java.util.List;

public class GameService {
    private static GameService instance = new GameService();

    private GameService() {
    }

    public static GameService getInstance() {
        return instance;
    }

    public String newGame() throws SQLException {
        Board board = BoardFactory.createBoard();

        updateToDB(board);
        return JSONGenerator.generateJSON(board);
    }

    public String move(MovingInfo movingInfo) throws SQLException {
        Board board = loadFromDB();

        try {
            board.move(movingInfo);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException error) {
            return error.getMessage();
        }
        updateToDB(board);
        return JSONGenerator.generateJSON(board);
    }

    public String continueGame() throws SQLException {
        Board board = loadFromDB();

        return JSONGenerator.generateJSON(board);
    }

    private Board loadFromDB() throws SQLException {
        BoardDAO boardDAO = BoardDAO.getInstance();
        List<ChessPieceDTO> chessPieces = boardDAO.loadBoard();
        GameStatusDTO gameStatusDTO = boardDAO.loadGameStatus();
        Team nowPlayingTeam = Team.of(gameStatusDTO.getNowPlayingTeam());
        boolean isGameEnd = Boolean.parseBoolean(gameStatusDTO.getIsGameEnd());
        GameStatus gameStatus = new GameStatus(nowPlayingTeam, isGameEnd);
        Board board = BoardFactory.createBlankBoard(gameStatus);

        setChessPiecesToBoard(chessPieces, board);
        return board;
    }

    private void setChessPiecesToBoard(List<ChessPieceDTO> chessPieces, Board board) {
        for (ChessPieceDTO chessPieceDTO : chessPieces) {
            String pieceName = chessPieceDTO.getPieceName();
            String teamName = chessPieceDTO.getTeam();
            int x = chessPieceDTO.getX();
            int y = chessPieceDTO.getY();
            Position position = Position.of(Coordinate.of(x), Coordinate.of(y));
            ChessPiece chessPiece = ChessPieceGenerator.generateChessPiece(pieceName, teamName, position);

            board.setPosition(chessPiece, position);
        }
    }

    private void updateToDB(Board board) throws SQLException {
        BoardDAO boardDAO = BoardDAO.getInstance();

        boardDAO.initializeBoard();
        boardDAO.initializeGameStatus();
        boardDAO.updateGameStatus(board.getGameStatus());
        updateBoard(board);
    }

    private void updateBoard(Board board) throws SQLException {
        List<Row> rows = board.getBoard();

        for (int i = 0; i < rows.size(); i++) {
            List<ChessPiece> chessPieces = rows.get(i).getChessPieces();

            updateRow(chessPieces, i);
        }
    }

    private void updateRow(List<ChessPiece> chessPieces, int i) throws SQLException {
        for (int j = 0; j < chessPieces.size(); j++) {
            ChessPiece chessPiece = chessPieces.get(j);

            addIfNotBlank(chessPiece, i, j);
        }
    }

    private void addIfNotBlank(ChessPiece chessPiece, int i, int j) throws SQLException {
        BoardDAO boardDAO = BoardDAO.getInstance();

        if (!(chessPiece instanceof Blank)) {
            boardDAO.updateChessPiece(chessPiece, i, j);
        }
    }
}
