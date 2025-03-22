package chess;

import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Chess {

    Map<Piece, Position> map;

    public void play() {

        map = new HashMap<>();

        map.put(new Queen(Color.WHITE), new Position(Row.ONE, Column.E));
        map.put(new Rook(Color.BLACK), new Position(Row.ONE, Column.D));
        OutputView.displayBoard(map);
        //사용자가 두 좌표를 입력
        Position beforePosition = new Position(Row.ONE, Column.E);
        Position afterPosition = new Position(Row.TWO, Column.F);

        move(beforePosition, afterPosition);
        OutputView.displayBoard(map);
    }

    private void move(Position beforePosition, Position afterPosition) {

        Piece piece = map.entrySet().stream().filter(e -> e.getValue().equals(beforePosition)).map(e -> e.getKey())
                .findAny().get();

        List<Movement> movements = null;
        if (piece.canMove(beforePosition, afterPosition)) {
            movements = piece.getMovements(beforePosition, afterPosition);
        }

        if (movements != null && piece.canMoveWithPieces(
                checkRoute(beforePosition, movements))) {
            Position position = beforePosition;
            if (map.values().contains(afterPosition)) {
                map.remove(map.entrySet().stream().filter(e -> e.getValue().equals(afterPosition)).map(e -> e.getKey())
                        .findAny().get());
            }
            for (Movement movement : Objects.requireNonNull(movements)) {
                position= position.move(movement);
            }
            map.put(piece, position);
            System.out.println("잘 이동됨");
        }
    }

    private Map<Piece, Boolean> checkRoute(Position beforePosition, List<Movement> movements) {
        List<Position> positions = beforePosition.moveSimulRoute(movements);
        Map<Piece, Boolean> m2 = new HashMap<>();
        List<Piece> pieces = map.entrySet().stream().filter(e -> positions.contains(e.getValue())).map(e -> e.getKey())
                .toList();
        for (int i = 0; i < pieces.size(); i++) {
            if (i == pieces.size() - 1) {
                m2.put(pieces.get(i), true);
                continue;
            }
            m2.put(pieces.get(i), false);
        }
        return m2;
    }
}

//TODO: 체스 좌표 두가지를 입력하면 해당 좌표로 이동한다.
//
