package web.controller;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.G;
import static chess.position.File.H;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.FIVE;
import static chess.position.Rank.FOUR;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.SIX;
import static chess.position.Rank.THREE;
import static chess.position.Rank.TWO;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import java.util.Map;

public class Movement {
    private final Position from;
    private final Position to;

    private static final Map<String, Rank> RANKS = Map.of(
            "1", ONE, "2", TWO, "3", THREE, "4", FOUR, "5", FIVE, "6", SIX, "7", SEVEN, "8", EIGHT
    );
    private static final Map<String, File> FILES = Map.of(
            "A", A, "B", B, "C", C, "D", D, "E", E, "F", F, "G", G, "H", H
    );

    public Movement(String body) {
        if (!body.matches("from=[A-H][1-8]&to=[A-H][1-8]")) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
        String[] positions = splitBody(body);
        from = new Position(file(positions[0]), rank(positions[0]));
        to = new Position(file(positions[1]), rank(positions[1]));
    }

    private String[] splitBody(String body) {
        body = body.replace("from=","");
        body = body.replace("to=","");
        return body.split("&");
    }

    private File file(String position) {
        return FILES.get(position.substring(0, 1));
    }

    private Rank rank(String position) {
        return RANKS.get(position.substring(1, 2));
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
