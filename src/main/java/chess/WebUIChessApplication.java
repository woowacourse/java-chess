package chess;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import chess.dto.PieceDto;
import chess.dto.PiecesDto;
import chess.view.PieceNameConverter;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFiles.location("/static");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            final ChessGame chessGame = new ChessGame(new BlackTeam(), new WhiteTeam());
            final Map<Position, String> chessBoard = convertToBlackPrintName(chessGame);
            chessBoard.putAll(convertToWhitePrintName(chessGame));
            List<PieceDto> pieceDtos = new ArrayList<>();
            for (Map.Entry<Position, String> entry : chessBoard.entrySet()) {
                pieceDtos.add(new PieceDto(entry.getKey().getKey(), entry.getValue()));
            }

            PiecesDto piecesDto = new PiecesDto(pieceDtos);

            String obj = new Gson().toJson(piecesDto);
            return obj;
        });

        get("/move", (req, res) -> {
            System.out.println("/move----------------------------------- \n " + req.queryParams("position"));
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static Map<Position, String> convertToBlackPrintName(final ChessGame chessGame) {
        final Map<Position, Piece> blackPosition = chessGame.getBlackTeam().getPiecePosition();
        final Map<Position, String> blackPrintFormat = new HashMap<>();
        for (Position position : blackPosition.keySet()) {
            final Piece piece = blackPosition.get(position);
            blackPrintFormat.put(position, PieceNameConverter.convert(piece).toUpperCase());
        }
        return blackPrintFormat;
    }

    private static Map<Position, String> convertToWhitePrintName(final ChessGame chessGame) {
        final Map<Position, Piece> whitePosition = chessGame.getWhiteTeam().getPiecePosition();
        final Map<Position, String> whitePrintFormat = new HashMap<>();
        for (Position position : whitePosition.keySet()) {
            final Piece piece = whitePosition.get(position);
            whitePrintFormat.put(position, PieceNameConverter.convert(piece).toLowerCase());
        }
        return whitePrintFormat;
    }
}
