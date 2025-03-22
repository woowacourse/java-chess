import chess.Board;
import chess.BoardCreator;
import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Position, Piece> generatePieces = BoardCreator.generate();
        Board board = new Board(generatePieces);

        while (true) {
            for (Row row : Row.values()) {
                for (Column column : Column.values()) {
                    Optional<Piece> piece = board.getPieceOfOptional(column, row);
                    if (piece.isEmpty()) {
                        System.out.print("-");
                    } else {
                        System.out.print(piece.get());
                    }

                }
                System.out.println();
            }

            System.out.println("출발 좌표를 입력해주세요. ex) 1,2");
            String userInputOfDeparture = sc.nextLine();
            String[] split1 = userInputOfDeparture.split(",");
            Column column1 = Column.isSameName(split1[0]);
            Row row1 = Row.isSameName(split1[1]);

            Position departure = new Position(column1, row1);
            Piece piece = board.getPiece(departure);

            System.out.println("%s를 선택하셨습니다. 도착 좌표를 입력해주세요".formatted(piece));

            String userInputOfArrival = sc.nextLine();
            String[] split2 = userInputOfArrival.split(",");
            Column column2 = Column.isSameName(split2[0]);
            Row row2 = Row.isSameName(split2[1]);
            Position arrival = new Position(column2, row2);

            board.move(departure, arrival);
        }
    }
}
