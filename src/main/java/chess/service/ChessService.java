package chess.service;

import chess.dao.BoardDAO;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.dto.WebBoardDTO;
import chess.dto.WebPiecesDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessService {

    public Map<String, String> start() {
        Board board = new Board();
        board.init();
        try {
            BoardDAO boardDAO = BoardDAO.instance();
            boardDAO.createBoard(new WebBoardDTO(board), new WebPiecesDTO(board.pieces()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Map<Position, Piece> pieces = board.pieces();
        return PositionToStringMap(pieces, board);
    }

    private Map<String, String> PositionToStringMap(Map<Position, Piece> pieces, Board board) {
        Map<String, String> boardInfo = new HashMap<>();
        for (Position position : pieces.keySet()) {
            boardInfo.put(position.positionToString(), getSymbol(pieces.get(position)));
        }
        boardInfo.put("isFinish", String.valueOf(board.isFinish()));
        boardInfo.put("turn", board.turn().color());
        return boardInfo;
    }

    private String getSymbol(Piece piece) {
        if (Objects.isNull(piece)) {
            return ".";
        }
        return piece.symbol();
    }

    public Map<String, String> movedPiece(int boardId ,String source, String target) {
        BoardDAO boardDAO = BoardDAO.instance();
        Board board = null;
        try {
            board = boardDAO.joinBoard(boardId);
            board.movePiece(source, target);
            WebBoardDTO webBoardDTO = new WebBoardDTO(board, boardId);
            WebPiecesDTO webPiecesDTO = new WebPiecesDTO(board.pieces(), source, target);
            boardDAO.updateBoard(webBoardDTO, webPiecesDTO);
            Map<Position, Piece> pieces = board.pieces();
            return PositionToStringMap(pieces, board);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<String> movablePositions(int boardId, String source) {
        List<String> positions = new ArrayList<>();
        BoardDAO boardDAO = BoardDAO.instance();
        Board board = null;
        try {
            board = boardDAO.joinBoard(boardId);
            for (Position position : board.movablePositions(source)) {
                positions.add(position.positionToString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return positions;
    }

    public Map<String, String> joinBoard(int boardId) {
        BoardDAO boardDAO = BoardDAO.instance();
        try {
            Board board = boardDAO.joinBoard(boardId);
            Map<Position, Piece> pieces = board.pieces();
            return PositionToStringMap(pieces, board);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
