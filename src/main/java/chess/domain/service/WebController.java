package chess.domain.service;

import chess.DAO.ChessBoardDAO;
import chess.domain.DTO.MoveRequestDTO;
import chess.domain.DTO.MoveResultDTO;
import chess.domain.DTO.pieceOnBoardDTO;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.result.ResultDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import spark.Request;
import spark.Response;

public class WebController {

    private static ChessBoard chessBoard;
    private static Gson gson;
    private static ChessBoardDAO chessBoardDAO = new ChessBoardDAO();

    public WebController(Gson gson) {
        WebController.gson = gson;
    }

    public static Object start(Request request, Response response) {
        Map<String, pieceOnBoardDTO> pieces = getAllPieces();
        return gson.toJson(pieces);
    }

    public static Object move(Request request, Response response) {
        MoveRequestDTO moveRequestDTO = gson.fromJson(request.body(), MoveRequestDTO.class);
        MoveResultDTO moveResultDTO;
        moveResultDTO = createMoveResultDTO(moveRequestDTO);

        return gson.toJson(moveResultDTO);
    }

    private static MoveResultDTO createMoveResultDTO(MoveRequestDTO moveRequestDTO) {
        ResultDto result;
        MoveResultDTO moveResultDTO;
        try {
            chessBoard.move(moveRequestDTO.getSource(), moveRequestDTO.getTarget());
            checkGameEnd();

            result = chessBoard.result();
            moveResultDTO = getMoveResultDTO(result, true);
        } catch (IllegalArgumentException exception) {
            result = chessBoard.result();
            moveResultDTO = getMoveResultDTO(result, false);
        }
        if (!chessBoard.isPlaying()) {
            moveResultDTO.setWinner(result.getWinner());
        }
        return moveResultDTO;
    }

    private static MoveResultDTO getMoveResultDTO(ResultDto result, boolean state) {
        return new MoveResultDTO(state,
            chessBoard.isPlaying(),
            result.whiteScore().getScore(),
            result.blackScore().getScore());
    }

    private static void checkGameEnd() {
        if (!chessBoard.isPlaying()) {
            throw new IllegalArgumentException("game End state");
        }
    }

    public static Object reset(Request request, Response response) {
        chessBoard = new ChessBoard();
        return request;
    }

    private static Map<String, pieceOnBoardDTO> getAllPieces() {
        Map<String, pieceOnBoardDTO> pieces = new HashMap<>();
        for (Entry<Position, Piece> entry : chessBoard.getChessBoard().entrySet()) {
            pieces.put(entry.getKey().getPosition(),
                new pieceOnBoardDTO(entry.getValue().getColor(),
                    entry.getValue().getPieceName()));
        }
        return pieces;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static Object save(Request request, Response response) {
        GsonBuilder builder = new GsonBuilder();
        try {
            chessBoardDAO.addRoomInformation("test3", chessBoard, gson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Object load(Request request, Response response) {
        try {
            chessBoard = chessBoardDAO.findChessBoardByRoom("test3", gson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
