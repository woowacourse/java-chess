package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Color;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard = new LinkedHashMap<>();

    public ChessBoard() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();

        chessBoard.put(Position.of('a', 8), Rook.of(Color.BLACK));
        chessBoard.put(Position.of('b', 8), Knight.of(Color.BLACK));
        chessBoard.put(Position.of('c', 8), Bishop.of(Color.BLACK));
        chessBoard.put(Position.of('d', 8), Queen.of(Color.BLACK));
        chessBoard.put(Position.of('e', 8), King.of(Color.BLACK));
        chessBoard.put(Position.of('f', 8), Bishop.of(Color.BLACK));
        chessBoard.put(Position.of('g', 8), Knight.of(Color.BLACK));
        chessBoard.put(Position.of('h', 8), Rook.of(Color.BLACK));

        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Position.of(file, 7), Pawn.of(Color.BLACK));
        }

        Map<Position, Piece> blankPieces = new LinkedHashMap<>();
        for (int rank = 6; rank >= 3; rank--) {
            for (char file = 'a'; file <= 'h'; file++) {
                blankPieces.put(Position.of(file, rank), null);
            }
        }

        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Position.of(file, 2), Pawn.of(Color.WHITE));
        }

        chessBoard.put(Position.of('a', 1), Rook.of(Color.WHITE));
        chessBoard.put(Position.of('b', 1), Knight.of(Color.WHITE));
        chessBoard.put(Position.of('c', 1), Bishop.of(Color.WHITE));
        chessBoard.put(Position.of('d', 1), Queen.of(Color.WHITE));
        chessBoard.put(Position.of('e', 1), King.of(Color.WHITE));
        chessBoard.put(Position.of('f', 1), Bishop.of(Color.WHITE));
        chessBoard.put(Position.of('g', 1), Knight.of(Color.WHITE));
        chessBoard.put(Position.of('h', 1), Rook.of(Color.WHITE));

    }
    public List<Piece> findAllPieces() {
        return chessBoard.values()
                .stream()
                .toList();
    }

    public Piece findPieceByPosition(final Position position) {
        return chessBoard.get(position);
    }

    public void move(final Position source, final Position target) {
        // 소스 위치에 해당하는 피스를 찾는다.
        Piece piece = chessBoard.get(source);

        chessBoard.put(target, piece);
        chessBoard.put(source, null);

        // 피스의 이동 영역에 타겟 피스가 포함되는지 검사한다.
        // boolean을 리턴하는 "내 이동 가능 영역에 타겟이 있는지 검사"하는 함수가 피스 안에 있어야 한다.
        // 그렇기 위해서는 피스를 리팩토링 해야 한다. 왜냐하면 그 가능 영역이 타입에 따라 다르니까/
        // 즉, 이 시점에서 대규모 리팩토링 필요하다. (추상 클래스를 구현하도록)
        // 커밋은 나중에 날린다고 생각하고 일단 ㄱㄱ한다.
        // 그리고 커밋 날리는거 복잡할 수 있으니까 일단 인텔리제이를 사용해서 커밋한다.

        // 이동 경로에 다른 피스가 있는지 검사한다. - 이때 나이트이면 검사하지 않는다.
        // 도착지에 다른 피스가 있는지 검사한다. - 그 피스를 확인하고, 다른 팀이면 그대로 이동, 같은 팀이면 에러를 띄운다.
    }
}
