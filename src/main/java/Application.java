import chess.Board;
import chess.Column;
import chess.Movement;
import chess.Piece;
import chess.Position;
import chess.Row;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final Board board = new Board();
        while (true) {
            System.out.println(board);
            System.out.println("이동할 기물을 선택하세요. 예시 : A,1");
            final String nextLine1 = scanner.nextLine();
            final Position Line1 = getPosition(nextLine1);
            System.out.println("이동할 위치를 선택하세요. 예시 : A,2");
            final String nextLine2 = scanner.nextLine();
            final Position Line2 = getPosition(nextLine2);
            final Map<Position, Piece> pieceMap = board.getPieceMap();

            final Piece piece1 = pieceMap.get(Line1);
            if (piece1 == null) {
                throw new IllegalArgumentException("선택한 위치에 피스가 없습니다.");
            }

            final List<Movement> movements = piece1.getPieceType()
                .getMovements(piece1.getColor());
            int count = 0;
            for (final Movement movement : movements) {
                final Position move = Line1.move(movement);
                if (move.equals(Line2)) {
                    count += 1;
                    break;
                }
            }
            if (count == 0) {
                throw new IllegalArgumentException("해당 피스는 해당 위치로 이동할 수 없습니다.");
            }

            final Piece piece2 = pieceMap.get(Line2);
            if (piece2 != null) {
                throw new IllegalArgumentException("해당 위치에는 말이 존재합니다.");
            }

            pieceMap.remove(Line1);
            pieceMap.put(Line2, piece1);
        }
    }

    private static Position getPosition(String line) {
        final String[] split = line.split(",");
        final Column column = Column.find(split[0].trim());
        final Row row = Row.find(split[1].trim());
        return new Position(row, column);
    }
}
