package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import chess.dto.PieceDto;
import chess.dto.PiecesDto;
import chess.dto.ResponseDto;
import chess.view.PieceNameConverter;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.get;

public class WebChessController {
    private ChessGame chessGame = null;
    private Gson gson = null;
    public void run() {
        gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

         get("/startChessGame", (req, res) -> {
             if (chessGame != null && !chessGame.isEnd()) {
                 return gson.toJson(new ResponseDto(false, "체스 게임이 시작 중 입니다."));
             }
             chessGame = new ChessGame(new BlackTeam(), new WhiteTeam());
             return gson.toJson(new ResponseDto(true, "체스가 시작되었습니다."));
        });

        get("/refreshChessBoard", (req, res) -> {
            final Map<Position, String> chessBoard = convertToBlackPrintName(chessGame);
            chessBoard.putAll(convertToWhitePrintName(chessGame));
            List<PieceDto> pieceDtos = new ArrayList<>();
            for (Map.Entry<Position, String> entry : chessBoard.entrySet()) {
                pieceDtos.add(new PieceDto(entry.getKey().getKey(), entry.getValue()));
            }

            PiecesDto piecesDto = new PiecesDto(pieceDtos);

            String obj = gson.toJson(piecesDto);
            return obj;
        });

        get("/selectPiece", (req, res) -> {
            if (chessGame == null || chessGame.isEnd()) {
                return gson.toJson(new ResponseDto(false, "체스 게임을 먼저 시작해주세요."));
            }
            Position position = Position.of(req.queryParams("position"));

            if (chessGame.havePieceInCurrentTurn(position)) {
                return gson.toJson(new ResponseDto(true, "기물을 선택 하셨습니다."));
            }

            return gson.toJson(new ResponseDto(false, "해당 위치에 기물이 없습니다."));
        });

        get("/movePiece", (req, res) -> {
            Position selected = Position.of(req.queryParams("selected"));
            Position target = Position.of(req.queryParams("target"));

            if (chessGame == null || chessGame.isEnd()) {
                return gson.toJson(new ResponseDto(false, "체스 게임을 먼저 시작해주세요."));
            }

            boolean isSuccess = false;
            try {
                isSuccess = chessGame.move(selected, target);
            } catch (IllegalArgumentException e) {
                isSuccess = false;
            }

            if (isSuccess) {
                return gson.toJson(new ResponseDto(true, "기물을 옮겼습니다."));
            }

            return gson.toJson(new ResponseDto(false, "기물을 옮기지 못했습니다."));
        });
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
    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
