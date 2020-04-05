package chess.controller;

import chess.domains.Record;
import chess.domains.board.Board;
import chess.domains.piece.PieceColor;
import chess.service.WebService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebController {
    public static Map<String, Object> makeModel(Board board, List<Record> records, Map<String, String> pieceCodes) {
        Map<String, Object> model = new HashMap<>();
        model.put("records", records);
        model.put("pieces", pieceCodes);
        model.put("turn", printTurn(WebService.turn(board)));
        model.put("white_score", WebService.calculateScore(board, PieceColor.WHITE));
        model.put("black_score", WebService.calculateScore(board, PieceColor.BLACK));

        return model;
    }

    public static Map<String, String> convertView(Map<String, String> board) {
        Map<String, String> pieces = new HashMap<>();
        for (String position : board.keySet()) {
            switch (board.get(position)) {
                case "r":
                    pieces.put(position, "♖");
                    break;
                case "n":
                    pieces.put(position, "♘");
                    break;
                case "b":
                    pieces.put(position, "♗");
                    break;
                case "k":
                    pieces.put(position, "♔");
                    break;
                case "q":
                    pieces.put(position, "♕");
                    break;
                case "p":
                    pieces.put(position, "♙");
                    break;
                case "R":
                    pieces.put(position, "♜");
                    break;
                case "N":
                    pieces.put(position, "♞");
                    break;
                case "B":
                    pieces.put(position, "♝");
                    break;
                case "K":
                    pieces.put(position, "♚");
                    break;
                case "Q":
                    pieces.put(position, "♛");
                    break;
                case "P":
                    pieces.put(position, "♟");
                    break;
                case ".":
                    pieces.put(position, "");
                    break;
            }
        }
        return pieces;
    }

    private static String printTurn(String turn) {
        return turn + "의 순서입니다.";
    }

}
