package chess.generator;

import chess.domain.board.Board;
import chess.domain.board.Row;
import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class JSONGenerator {
    private JSONGenerator() {
    }

    public static String generateJSON(Board board) {
        JSONObject jsonObject = new JSONObject();
        JSONArray chessPieceArray = new JSONArray();

        List<Row> rows = board.getBoard();
        for (int i = 0; i < rows.size(); i++) {
            List<ChessPiece> chessPieces = rows.get(i).getChessPieces();
            addRowData(chessPieceArray, chessPieces, i);
        }
        jsonObject.put("chessPieces", chessPieceArray);
        return jsonObject.toJSONString();
    }

    private static void addRowData(JSONArray chessPieceArray, List<ChessPiece> chessPieces, int i) {
        for (int j = 0; j < chessPieces.size(); j++) {
            ChessPiece chessPiece = chessPieces.get(j);

            addColumnData(chessPieceArray, chessPiece, i, j);
        }
    }

    private static void addColumnData(JSONArray chessPieceArray, ChessPiece chessPiece, int i, int j) {
        if (!(chessPiece instanceof Blank)) {
            JSONObject chessPieceInfo = new JSONObject();

            chessPieceInfo.put("name", chessPiece.getName());
            chessPieceInfo.put("team", chessPiece.getTeam().getTeamName());
            chessPieceInfo.put("x", i + 1);
            chessPieceInfo.put("y", j + 1);
            chessPieceArray.add(chessPieceInfo);
        }
    }
}
