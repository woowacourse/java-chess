package chess;

import chess.domain.Board;
import chess.domain.Spot;
import chess.domain.Team;
import chess.domain.piece.Piece;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Map;

public class BoardJson {
    private Board board;

    public BoardJson(Board board) {
        this.board = board;
    }

    public JsonObject getBoardJson() {
        JsonArray jsonObject = new JsonArray();
        jsonObject.add(teamJson(Team.BLACK));
        jsonObject.add(teamJson(Team.WHITE));

        JsonObject boardInfo = new JsonObject();
        boardInfo.add("Board", jsonObject);
        return boardInfo;
    }

    private JsonObject teamJson(Team team) {
        Map<Spot, Piece> teamPieces = board.getTeamPieces(team);

        JsonArray teamJson = new JsonArray();
        teamPieces.entrySet()
                .stream()
                .forEach(spotPieceEntry -> {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("Spot", spotPieceEntry.getKey().getIndex());
                    jsonObject.addProperty("Piece", String.valueOf(spotPieceEntry.getValue().getPieceType()));
                    teamJson.add(jsonObject);
                });

        JsonObject teamInfo = new JsonObject();
        teamInfo.add(team.getTeamColor(), teamJson);
        return teamInfo;
    }
}

