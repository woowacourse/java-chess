package chess;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {

    private static final String MOVE_SUCCESS = "";

    private JdbcChessBoardDAO jdbcChessBoardDAO;
    private ChessBoard chessBoard;

    public WebChessController() {
        jdbcChessBoardDAO = new JdbcChessBoardDAO();
        staticFileLocation("templates");
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/chessStart", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            jdbcChessBoardDAO.initialChessBoard();

            chessBoard = new ChessBoard(jdbcChessBoardDAO.setBoard());

            settingChessBoard(model);

            return render(model, "contents/chess.html");
        });

        get("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessBoard = new ChessBoard(jdbcChessBoardDAO.setBoard(), jdbcChessBoardDAO.selectPlayerTurn());

            settingChessBoard(model);
            return render(model, "contents/chess.html");
        });


        post("/move", (req, res) -> {
            String sourcePosition = req.queryParams("source");
            String targetPosition = req.queryParams("target");
            PieceColor playerTurn = chessBoard.getPlayerColor();
            ChessBoardDTO chessBoardDTO = new ChessBoardDTO(sourcePosition, targetPosition, playerTurn);

            try {
                moveChessBoard(sourcePosition, targetPosition, chessBoardDTO);
                chessBoard.playerTurnChange();
                chessBoardDTO.setPieceColor(chessBoard.getPlayerColor());
                jdbcChessBoardDAO.updatePlayerTurn(chessBoardDTO);
                if (chessBoard.isCaughtKing()) {
                    return chessBoard.getPlayerColor().getColor() + "이 승리했습니다!";
                }
                return MOVE_SUCCESS;
            } catch (Exception e) {
                res.status(403);
                return e.getMessage();
            }
        });

        post("/status", (req, res) -> {
            PieceColor playerTurn = chessBoard.getPlayerColor();
            res.body(String.format("%s점수: %.1f", playerTurn.getColor(), chessBoard.calculateScore()));
            return res.body();
        });
    }

    private void moveChessBoard(String sourcePosition, String targetPosition, ChessBoardDTO chessBoardDTO) {
        if (chessBoard.containsTargetPosition(Position.of(targetPosition))) {
            chessBoard.move(Position.of(sourcePosition), Position.of(targetPosition));
            jdbcChessBoardDAO.deleteCaughtPiece(chessBoardDTO);
            jdbcChessBoardDAO.updatePiece(chessBoardDTO);
            return;
        }
        chessBoard.move(Position.of(sourcePosition), Position.of(targetPosition));
        jdbcChessBoardDAO.updatePiece(chessBoardDTO);
    }

    private void settingChessBoard(Map<String, Object> model) {
        List<ChessPieceDTO> chessPieceDTOArrayList = new ArrayList<>();

        for (Map.Entry<Position, ChessPiece> entry : chessBoard.getChessBoard().entrySet()) {
            ChessPieceDTO chessPieceDTO =
                    new ChessPieceDTO(entry.getKey().getPositionToString(), entry.getValue().getName());

            chessPieceDTOArrayList.add(chessPieceDTO);
        }
        model.put("chessPiece", chessPieceDTOArrayList);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
