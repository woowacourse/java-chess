package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessGameDto {
    private final String name;

    private final ChessGame chessGame;

    public ChessGameDto(String name, ChessGame chessGame) {
        this.name = name;
        this.chessGame = chessGame;
    }

    public ChessGameDto(ChessGame chessGame) {
        this.name = "";
        this.chessGame = chessGame;
    }

    public String getName() {
        return name;
    }

    public List<PieceDto> getBoardWeb() {
        List<PieceDto> result = new ArrayList<>();
        for (Rank rank : Rank.reverseRanks()) {
            addPiecesDtoRank(result, chessGame.getBoard().getSquares(), rank);
        }
        return result;
    }

    public String getBoardJson() {
        JsonArray jsonElements = new JsonArray();
        for (Entry<Position, Piece> entry : chessGame.getBoard().getSquares().entrySet()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("position", entry.getKey().toString());
            jsonObject.addProperty("piece", entry.getValue().getName().getValue(entry.getValue().getTeam()));
            jsonElements.add(jsonObject);
        }
        return new Gson().toJson(jsonElements);
    }

    private void addPiecesDtoRank(List<PieceDto> result, Map<Position, Piece> squares, Rank rank) {
        for (Column column : Column.values()) {
            Piece piece = squares.get(new Position(column, rank));
            result.add(new PieceDto(piece));
        }
    }

    public Map<Position, Piece> getSquares() {
        return chessGame.getBoard().getSquares();
    }

    public ChessGame getChessGame() {
        return chessGame;
    }
}
