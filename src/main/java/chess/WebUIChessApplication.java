package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.ChessWebController;
import chess.domain.board.Position;
import chess.domain.dao.ChessDAO;
import chess.domain.dto.PieceDTO;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private final static Gson gson = new Gson();

    public static void main(String[] args) {
        ChessDAO chessDAO = new ChessDAO();
        chessDAO.getConnection();
        ChessWebController webController = new ChessWebController();

        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();

            return render(submitData, "Chess.html");
        });

        post("/load", (req,res) -> {
            Map<String, Object> submitData = new HashMap<>();
            Map<String, Object> loadedSubmitBoard = new HashMap<>();
            webController.loadGame(chessDAO);
            Map<Position, Piece> loadedBoard = webController.getBoard();
            translateBoard(loadedSubmitBoard, loadedBoard);

            submitData.put("turn", webController.getTurn());
            submitData.put("chessBoard", loadedSubmitBoard);
            return gson.toJson(submitData);
        });

        post("/initial", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();
            Map<String, Object> initialChessBoard = new HashMap<>();
            Map<Position, Piece> startedBoard= webController.startedBoard();
            chessDAO.initTurn();
            insertInitialBoard(initialChessBoard, startedBoard, chessDAO);

            submitData.put("turn", webController.getTurn());
            submitData.put("chessBoard", initialChessBoard);
            return gson.toJson(submitData);
        });

        post("/move", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();
            Map<String, Object> requestBody = gson.fromJson(req.body(), HashMap.class);
            String moveRawCommand = (String) requestBody.get("move");
            String moveResult = webController.move(moveRawCommand);
            submitData.put("isSuccess", moveResult);

            if (moveResult.equals("true")) {
                moveOnDB(chessDAO, moveRawCommand);
                chessDAO.updateTurn(webController.getTurn());
            }

            judgeEnd(webController, submitData);
            submitData.put("turn", webController.getTurn());
            return gson.toJson(submitData);
        });

        post("/grade", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();
            submitData.put("whiteScore", webController.whiteScore());
            submitData.put("blackScore", webController.blackScore());
            return gson.toJson(submitData);
        });

        post("/end", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();
            webController.end();
            return gson.toJson(submitData);
        });
    }

    private static void judgeEnd(ChessWebController webController,
        Map<String, Object> submitData) {
        if (webController.isEnd()) {
            submitData.put("winner", webController.winnerColor());
        }
    }

    private static void insertInitialBoard(Map<String, Object> initialChessBoard,
        Map<Position, Piece> startedBoard, ChessDAO chessDAO) throws SQLException {
        chessDAO.resetPiece(1);
        for (Map.Entry<Position, Piece> elem : startedBoard.entrySet()) {
            Position position = elem.getKey();
            Piece piece = elem.getValue();

            initialChessBoard.put(position.symbol(), piece.symbol());
            PieceDTO pieceDTO = processedPieceDTO(position, piece);

            chessDAO.addPiece(pieceDTO);
        }
    }

    public static void translateBoard(Map<String, Object> loadedSubmitBoard,
        Map<Position, Piece> loadedBoard) {
        for (Map.Entry<Position, Piece> elem : loadedBoard.entrySet()) {
            Position position = elem.getKey();
            Piece piece = elem.getValue();

            loadedSubmitBoard.put(position.symbol(), piece.symbol());
        }
    }

    public static PieceDTO processedPieceDTO(Position position, Piece piece) {
        return new PieceDTO(position.symbol(), piece.symbol());
    }

    public static void moveOnDB(ChessDAO chessDAO, String rawMoveCommand) throws SQLException {
        List<String> moveSourceTarget = Arrays.asList(rawMoveCommand.split(" "));
        PieceDTO pieceDTO = chessDAO.pieceOnLocation(moveSourceTarget.get(1),1);
        pieceDTO.setLocation(moveSourceTarget.get(2));
        PieceDTO voidPiece = new PieceDTO(moveSourceTarget.get(1), ".");
        System.out.println("hihi");
        chessDAO.deletePiece(moveSourceTarget.get(1),1);
        chessDAO.deletePiece(moveSourceTarget.get(2),1);
        chessDAO.addPiece(pieceDTO);
        chessDAO.addPiece(voidPiece);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}