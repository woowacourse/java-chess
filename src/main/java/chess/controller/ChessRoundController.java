package chess.controller;

import chess.application.chessround.ChessRoundService;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.chessround.dto.ChessPieceDTO;
import chess.domain.chessround.dto.ChessPlayerDTO;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

public class ChessRoundController {
    public static final String PATH_CHESS_ROUND = "/chess-round";

    public static final Route fetchChessRound = (req, res) -> {
        Map<String, Object> model = new HashMap<>();

        // 64개 칸을 빈 칸으로 초기화
        List<List<String>> pieces = new ArrayList<>();
        for (int row = ChessPoint.START; row <= ChessPoint.END; row++) {
            List<String> tmp = new ArrayList<>();
            for (int column = ChessPoint.START; column <= ChessPoint.END; column++) {
                tmp.add("\u00A0");
            }
            pieces.add(tmp);
        }

        //
        ChessRoundService chessRoundService = ChessRoundService.getInstance();
        ChessPlayerDTO chessPlayerDTO = chessRoundService.fetchWhitePlayer();

        for (ChessPieceDTO chessPieceDTO : chessPlayerDTO.getChessPieceDTOs()) {
            List<String> rowPieces = pieces.get(chessPieceDTO.getRow() - 1);
            rowPieces.set(chessPieceDTO.getColumn() - 1, chessPieceDTO.getName());
            pieces.set(chessPieceDTO.getRow() - 1, rowPieces);

            System.out.println("********************************************\n" + Arrays.toString(rowPieces.toArray()));
        }

        model.put("pieces", pieces);
        return new HandlebarsTemplateEngine().render(
                new ModelAndView(model, "index.hbs")
        );
    };

    private ChessRoundController() {
    }
}
