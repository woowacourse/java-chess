package chess.domain.piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.domain.board.Position;
import chess.domain.player.Player;

public class GamePiece {

    public static final GamePiece EMPTY = new GamePiece(null, null, ".");

    private final ChessPiece chessPiece;
    private final Player player;
    private final String name;

    private GamePiece(ChessPiece chessPiece, Player player, String name) {
        this.chessPiece = chessPiece;
        this.player = player;
        this.name = name;
    }

    public static GamePiece of(ChessPiece piece, Player player) {
        return new GamePiece(piece, player, player.decideName(piece.getName()));
    }

    // TODO: 2020/03/25 싱글톤

    public static List<GamePiece> list() {
        List<GamePiece> gamePieces = new ArrayList<>();
        for (ChessPiece piece : ChessPiece.values()) {
            for (Player player : Player.values()) {
                gamePieces.add(GamePiece.of(piece, player));
            }
        }

        return gamePieces;
    }

    public List<Position> searchPath(Position source, Position target, boolean isKill) {
        return chessPiece.searchPath(source, target, isKill);
    }

    public boolean isEnemy(GamePiece sourcePiece) {
        return player != sourcePiece.player;
    }

    public List<Position> getInitialPositions() {
        List<Position> positions = chessPiece.getOriginalPositions()
                .stream()
                .map(player::reviseInitialPosition)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(positions);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GamePiece gamePiece = (GamePiece) o;
        return chessPiece == gamePiece.chessPiece &&
                player == gamePiece.player &&
                Objects.equals(name, gamePiece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chessPiece, player, name);
    }
}
