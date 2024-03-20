package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard = new LinkedHashMap<>();

    public ChessBoard() {
        // 흑팀 말 배치
        chessBoard.put(Position.of('a', 8), new Piece(PieceType.ROOK, Team.BLACK));
        chessBoard.put(Position.of('b', 8), new Piece(PieceType.KNIGHT, Team.BLACK));
        chessBoard.put(Position.of('c', 8), new Piece(PieceType.BISHOP, Team.BLACK));
        chessBoard.put(Position.of('d', 8), new Piece(PieceType.QUEEN, Team.BLACK));
        chessBoard.put(Position.of('e', 8), new Piece(PieceType.KING, Team.BLACK));
        chessBoard.put(Position.of('f', 8), new Piece(PieceType.BISHOP, Team.BLACK));
        chessBoard.put(Position.of('g', 8), new Piece(PieceType.KNIGHT, Team.BLACK));
        chessBoard.put(Position.of('h', 8), new Piece(PieceType.ROOK, Team.BLACK));
        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Position.of(file, 7), new Piece(PieceType.PAWN, Team.BLACK));
        }

        // 비어있는 칸들 초기화 (3~6 랭크)
        for (int rank = 3; rank <= 6; rank++) {
            for (char file = 'a'; file <= 'h'; file++) {
                chessBoard.put(Position.of(file, rank), null);
            }
        }

        // 백팀 말 배치
        for (char file = 'a'; file <= 'h'; file++) {
            chessBoard.put(Position.of(file, 2), new Piece(PieceType.PAWN, Team.WHITE));
        }
        chessBoard.put(Position.of('a', 1), new Piece(PieceType.ROOK, Team.WHITE));
        chessBoard.put(Position.of('b', 1), new Piece(PieceType.KNIGHT, Team.WHITE));
        chessBoard.put(Position.of('c', 1), new Piece(PieceType.BISHOP, Team.WHITE));
        chessBoard.put(Position.of('d', 1), new Piece(PieceType.QUEEN, Team.WHITE));
        chessBoard.put(Position.of('e', 1), new Piece(PieceType.KING, Team.WHITE));
        chessBoard.put(Position.of('f', 1), new Piece(PieceType.BISHOP, Team.WHITE));
        chessBoard.put(Position.of('g', 1), new Piece(PieceType.KNIGHT, Team.WHITE));
        chessBoard.put(Position.of('h', 1), new Piece(PieceType.ROOK, Team.WHITE));
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
