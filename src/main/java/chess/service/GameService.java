package chess.service;

import chess.dao.BoardDAO;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
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
    private static BoardDAO boardDAO;

    public GameService() {
        boardDAO = new BoardDAO();
    }

    public String newGame() throws SQLException {
        Board board = BoardFactory.createBoard();

        boardDAO.updateDB(board);
        return JSONGenerator.generateJSON(board);
    }

    public String move(MovingInfo movingInfo) throws SQLException {
        Board board = loadFromDB();

        try {
            board.move(movingInfo);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException error) {
            return error.getMessage();
        }
        boardDAO.updateDB(board);
        return JSONGenerator.generateJSON(board);
    }

    public String continueGame() throws SQLException {
        Board board = loadFromDB();

        return JSONGenerator.generateJSON(board);
    }

    private Board loadFromDB() throws SQLException {
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
}
