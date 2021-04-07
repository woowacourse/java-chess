package chess.domain.service;

import chess.dao.ChessBoardDAO;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.dto.MoveRequestDTO;
import chess.domain.dto.MoveResultDTO;
import chess.domain.dto.PieceOnBoardDTO;
import chess.domain.piece.Piece;
import chess.domain.result.ResultDto;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private static ChessBoard chessBoard;
    private static Gson gson;
    private static ChessBoardDAO chessBoardDAO = new ChessBoardDAO();

    public WebController(Gson gson) {
        WebController.gson = gson;
    }

    public static Object start(Request request, Response response) {
        Map<String, PieceOnBoardDTO> pieces = getAllPieces();
        return gson.toJson(pieces);
    }

    public static Object move(Request request, Response response) throws SQLException {
        MoveRequestDTO moveRequestDTO = gson.fromJson(request.body(), MoveRequestDTO.class);
        MoveResultDTO moveResultDTO;
        moveResultDTO = createMoveResultDTO(moveRequestDTO);

        return gson.toJson(moveResultDTO);
    }

    private static MoveResultDTO createMoveResultDTO(MoveRequestDTO moveRequestDTO)
        throws SQLException {
        ResultDto result;
        MoveResultDTO moveResultDTO;
        try {
            chessBoard.move(moveRequestDTO.getSource(), moveRequestDTO.getTarget());
            chessBoardDAO.addLog(moveRequestDTO.getSource(), moveRequestDTO.getTarget());
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

    public static Object reset(Request request, Response response) throws SQLException {
        chessBoard = new ChessBoard();
        chessBoardDAO.clearLog();
        return request;
    }

    private static Map<String, PieceOnBoardDTO> getAllPieces() {
        Map<String, PieceOnBoardDTO> pieces = new HashMap<>();
        for (Entry<Position, Piece> entry : chessBoard.getChessBoard().entrySet()) {
            pieces.put(entry.getKey().getPosition(),
                new PieceOnBoardDTO(entry.getValue().getColor(),
                    entry.getValue().getPieceName()));
        }
        return pieces;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static Object load(Request request, Response response) throws SQLException {
        ChessBoard chessBoard = new ChessBoard();
        List<MoveRequestDTO> moveRequestDTOs = chessBoardDAO.getLog();
        for (MoveRequestDTO log : moveRequestDTOs) {
            chessBoard.move(log.getSource(), log.getTarget());
        }
        WebController.chessBoard = chessBoard;
        return "";
    }

    public static Object main(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
