package chess.domain.board;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author heebg
 * @version 1.0 2019-06-26
 */
public class Vectors {
    private final Set<Vector> vectors;

    public Vectors(Set<Vector> vectors) {
        this.vectors = vectors;
    }

    public void add(Vector vector) {
        vectors.add(vector);
    }

    public void remove(Vector vector) {
        vectors.remove(vector);
    }

    public Vectors removeSource(Square source) {
        Set<Vector> removeSource = vectors.stream()
                .filter(vector -> !(vector.getSquare().equals(source)))
                .collect(Collectors.toSet());
        return new Vectors(removeSource);
    }

    public Vectors removeSameLines(Square source) {
        Set<Vector> remove = vectors.stream()
                .filter(vector -> !(source.isLocatedSameLine(vector.getSquare())))
                .collect(Collectors.toSet());
        return new Vectors(remove);
    }

    public Vectors removeCurrentPlayers(Player currentPlayer) {
        Set<Vector> remove = vectors.stream()
                .filter(vector -> !(currentPlayer.contains(vector)))
                .collect(Collectors.toSet());
        return new Vectors(remove);
    }

    public Vectors removeOpponentPlayer(Player opponentPlayer) {
        Set<Vector> crossTarget = vectors.stream()
                .filter(vector -> vector.getDirection().equals(Direction.DOWN_LEFT) ||
                        vector.getDirection().equals(Direction.DOWN_RIGHT) ||
                        vector.getDirection().equals(Direction.UP_LEFT) ||
                        vector.getDirection().equals(Direction.UP_RIGHT))
                .filter(vector -> !(opponentPlayer.contains(vector)))
                .collect(Collectors.toSet());

        Set<Vector> linearTarget = vectors.stream()
                .filter(vector -> vector.getDirection().equals(Direction.UP) ||
                        vector.getDirection().equals(Direction.DOWN))
                .filter(vector -> (opponentPlayer.contains(vector)))
                .collect(Collectors.toSet());

        vectors.removeAll(crossTarget);
        vectors.removeAll(linearTarget);

        return new Vectors(vectors);
    }

    public Vectors removeKingPath(Player opponentPlayer) {
        Set<Vector> remove = vectors.stream()
                .filter(vector -> !opponentPlayer.getKingPath().contains(vector.getSquare()))
                .collect(Collectors.toSet());

        return new Vectors(remove);
    }

    public Vectors removeObstacles(Player blackPlayer, Player whitePlayer) {
        Set<Vector> existingList = playerHasArea(blackPlayer, whitePlayer);

        for (Vector vector : existingList) {
            vectors.removeAll(vector.getList());
        }

        return new Vectors(vectors);
    }

    private Set<Vector> playerHasArea(Player blackPlayer, Player whitePlayer) {
        Set<Vector> existingList = new HashSet<>();
        for (Vector vector : vectors) {
            if (blackPlayer.contains(vector)) {
                existingList.add(vector);
            }

            if (whitePlayer.contains(vector)) {
                existingList.add(vector);
            }
        }
        return existingList;
    }

    public void checkTarget(Square target) {
        vectors.stream()
                .filter(vector -> vector.getSquare().equals(target))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public Set<Square> generateSquares() {
        return vectors.stream().map(Vector::getSquare).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vectors vectors1 = (Vectors) o;
        return Objects.equals(vectors, vectors1.vectors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vectors);
    }
}
