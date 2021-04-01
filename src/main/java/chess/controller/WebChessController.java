package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import chess.dto.*;
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
                return gson.toJson(new ResponseDTO(false, "", "체스 게임이 시작 중 입니다."));
            }
            chessGame = new ChessGame(new BlackTeam(), new WhiteTeam());
            String jsonObj = gson.toJson(createChessGameDto(chessGame));
            return gson.toJson(new ResponseDTO(true, jsonObj, "체스가 시작되었습니다."));
        });

        get("/endChessGame", (req, res) -> {
            if (chessGame != null && !chessGame.isEnd()) {
                chessGame.finish();
                String jsonObj = gson.toJson(createChessGameDto(chessGame));
                return gson.toJson(new ResponseDTO(true, jsonObj, "체스 게임이 종료 되었습니다."));
            }
            return gson.toJson(new ResponseDTO(false, "", "체스 게임이 실행 중이 아닙니다."));
        });

        get("/selectPiece", (req, res) -> {
            if (chessGame == null || chessGame.isEnd()) {
                return gson.toJson(new ResponseDTO(false, "", "체스 게임을 먼저 시작해주세요."));
            }
            Position position = Position.of(req.queryParams("position"));

            if (chessGame.havePieceInCurrentTurn(position)) {
                return gson.toJson(new ResponseDTO(true, "", "기물을 선택 하셨습니다."));
            }

            return gson.toJson(new ResponseDTO(false, "", "잘못 선택 하셨습니다."));
        });

        get("/movePiece", (req, res) -> {
            Position selected = Position.of(req.queryParams("selected"));
            Position target = Position.of(req.queryParams("target"));

            if (chessGame == null || chessGame.isEnd()) {
                return gson.toJson(new ResponseDTO(false, "", "체스 게임을 먼저 시작해주세요."));
            }

            boolean isSuccess = false;

            try {
                isSuccess = chessGame.move(selected, target);
            } catch (IllegalArgumentException e) {
                isSuccess = false;
            }

            if (isSuccess) {
                String jsonObj = gson.toJson(createChessGameDto(chessGame));
                return gson.toJson(new ResponseDTO(true, jsonObj, "기물을 옮겼습니다."));
            }

            return gson.toJson(new ResponseDTO(false, "", "기물을 옮기지 못했습니다."));
        });
    }

    private ChessGameDTO createChessGameDto(ChessGame chessGame) {
        final Map<Position, String> chessBoard = convertToBlackPrintName(chessGame);
        chessBoard.putAll(convertToWhitePrintName(chessGame));
        List<PieceDTO> pieceDtos = new ArrayList<>();
        for (Map.Entry<Position, String> entry : chessBoard.entrySet()) {
            pieceDtos.add(new PieceDTO(entry.getKey().getKey(), entry.getValue()));
        }

        PiecesDTO piecesDto = new PiecesDTO(pieceDtos);

        TeamDTO blackTeamDTO = new TeamDTO(chessGame.getBlackTeam().getName(),
                String.valueOf(chessGame.getBlackTeam().calculateTotalScore()),
                chessGame.getBlackTeam().isCurrentTurn());

        TeamDTO whiteTeamDTO = new TeamDTO(chessGame.getWhiteTeam().getName(),
                String.valueOf(chessGame.getWhiteTeam().calculateTotalScore()),
                chessGame.getWhiteTeam().isCurrentTurn());

        ChessGameDTO chessGameDto = new ChessGameDTO(piecesDto, blackTeamDTO, whiteTeamDTO, !chessGame.isEnd());
        return chessGameDto;
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
