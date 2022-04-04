package chess.domain.player;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.movable.Pawn;
import chess.domain.piece.movable.multiple.Bishop;
import chess.domain.piece.movable.multiple.Queen;
import chess.domain.piece.movable.multiple.Rook;
import chess.domain.piece.movable.single.King;
import chess.domain.piece.movable.single.Knight;

public class PlayerFactory {

    private static final PlayerFactory PLAYER_FACTORY = new PlayerFactory();

    private PlayerFactory() {
    }

    public static PlayerFactory getInstance() {
        return PLAYER_FACTORY;
    }

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(createBlackPlayer());
        players.add(createWhitePlayer());
        return players;
    }

    private Player createBlackPlayer() {
        Map<Position, Piece> blackPieces = new HashMap<>(Map.of(
                Position.from("a8"), Rook.getInstance(),
                Position.from("b8"), Knight.getInstance(),
                Position.from("c8"), Bishop.getInstance(),
                Position.from("d8"), Queen.getInstance(),
                Position.from("e8"), King.getInstance(),
                Position.from("f8"), Bishop.getInstance(),
                Position.from("g8"), Knight.getInstance(),
                Position.from("h8"), Rook.getInstance()
        ));
        for (char i = 0; i < 8; i++) {
            blackPieces.put(Position.of((char) ('a' + i), '7'), Pawn.getBlackPawn());
        }
        return Player.of(BLACK, blackPieces);
    }

    private Player createWhitePlayer() {
        Map<Position, Piece> whitePieces = new HashMap<>(Map.of(
                Position.from("a1"), Rook.getInstance(),
                Position.from("b1"), Knight.getInstance(),
                Position.from("c1"), Bishop.getInstance(),
                Position.from("d1"), Queen.getInstance(),
                Position.from("e1"), King.getInstance(),
                Position.from("f1"), Bishop.getInstance(),
                Position.from("g1"), Knight.getInstance(),
                Position.from("h1"), Rook.getInstance()
        ));
        for (char i = 0; i < 8; i++) {
            whitePieces.put(Position.of((char) ('a' + i), '2'), Pawn.getWhitePawn());
        }
        return Player.of(WHITE, whitePieces);
    }
}
