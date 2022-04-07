package study;

import chess.domain.ChessBoard;
import chess.domain.member.Member;
import chess.domain.member.Role;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.domain.position.Position;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class GsonTest {

    @Test
    void createJson() {
        Gson gson = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "roma");
        jsonObject.addProperty("age", "20");

        String jsonStr = gson.toJson(jsonObject);
        System.out.println(jsonStr);
    }

    @Test
    void memberCreateJson() {
        Gson gson = new Gson();
        Member member = new Member("kbs", "로마", new Role("대장"));
        System.out.println(gson.toJson(member));
    }

    @Test
    void createJsonByMap1() {
        Gson gson = new Gson();
        ChessBoard chessBoard = new ChessBoard(() -> new HashMap<>(Map.ofEntries(Map.entry(
                Position.of("a4"), new Pawn(Color.BLACK)))
        ));

        Map<Position, Piece> pieces = chessBoard.getPieces();
        System.out.println(gson.toJson(pieces));
    }

    @Test
    void createJsonByMap2() {
        Gson gson = new Gson();
        ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());
        Map<Position, Piece> pieces = chessBoard.getPieces();
        System.out.println(gson.toJson(pieces));
    }
}
