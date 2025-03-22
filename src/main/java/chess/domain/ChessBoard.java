package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.None;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, ChessPiece> board = new HashMap<>();

    public ChessBoard() {
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                board.put(new Position(row, column), new None());
            }
        }

        board.put(new Position(Row.EIGHT, Column.A), new Rook(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.B), new Knight(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.C), new Bishop(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.D), new Queen(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.E), new King(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.F), new Bishop(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.G), new Knight(Color.BLACK));
        board.put(new Position(Row.EIGHT, Column.H), new Rook(Color.BLACK));

        board.put(new Position(Row.SEVEN, Column.A), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.B), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.C), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.D), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.E), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.F), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.G), new BlackPawn());
        board.put(new Position(Row.SEVEN, Column.H), new BlackPawn());

        board.put(new Position(Row.ONE, Column.A), new Rook(Color.WHITE));
        board.put(new Position(Row.ONE, Column.B), new Knight(Color.WHITE));
        board.put(new Position(Row.ONE, Column.C), new Bishop(Color.WHITE));
        board.put(new Position(Row.ONE, Column.D), new Queen(Color.WHITE));
        board.put(new Position(Row.ONE, Column.E), new King(Color.WHITE));
        board.put(new Position(Row.ONE, Column.F), new Bishop(Color.WHITE));
        board.put(new Position(Row.ONE, Column.G), new Knight(Color.WHITE));
        board.put(new Position(Row.ONE, Column.H), new Rook(Color.WHITE));

        board.put(new Position(Row.TWO, Column.A), new WhitePawn());
        board.put(new Position(Row.TWO, Column.B), new WhitePawn());
        board.put(new Position(Row.TWO, Column.C), new WhitePawn());
        board.put(new Position(Row.TWO, Column.D), new WhitePawn());
        board.put(new Position(Row.TWO, Column.E), new WhitePawn());
        board.put(new Position(Row.TWO, Column.F), new WhitePawn());
        board.put(new Position(Row.TWO, Column.G), new WhitePawn());
        board.put(new Position(Row.TWO, Column.H), new WhitePawn());
    }

    public ChessPiece getPieceOfPosition(Position position) {
        return board.get(position);
    }

    // 1. 도착지 자체가 말이 갈 수 있는 도착지인지 확인(Piece.canMove에 출발, 도착지 넘겨줌) O
    // 2. 장애물 있는지 확인 O
    // 3. 도착지 말 색 확인 O
    public void movePiece(Position origin, Position destination) {

        ChessPiece movePiece = getPieceOfPosition(origin);
        movePiece.validateCanMove(origin, destination); // 움직일 수 있는 경로에 있는지 확인


        // 나이트
        if (movePiece.getClass().equals(Knight.class)) {
            // 장애물 있어도 됨
        }
        // 폰
        else if (movePiece.getClass().equals(BlackPawn.class) || movePiece.getClass().equals(WhitePawn.class)) {
            // 첫 움직임 -> 앞으로 두칸까지 가능
            Pawn pawn = (Pawn) movePiece;
            Movement route = pawn.findRoute(origin, destination).get(0);

            // if route가 대각선 -> 도착지에 상대 말 있어야 움직일 수 있음 -> 무조건 먹음이 일어남
            if (route.isDiagonal()) {
                if (pawn.getColor() != getPieceOfPosition(destination).getColor().opposite()) {
                    throw new IllegalStateException("대각선 위치에 상대 말이 없기 때문에 움직일 수 없습니다.");
                }
                getPieceOfPosition(destination).capture();
            }
            // 앞으로 움직임 -> 도착지와 경로에 장애물 없으면 가능F
            else {
                validateExistHurdleOnRouteWithDestination(pawn, origin, destination);
            }

            pawn.isMoved();
        }

        // 나머지 말
        else {
            // 장애물 있는지 확인
            validateExistHurdleOnRouteWithoutDestination(movePiece, origin, destination);
        }

        ChessPiece targetPiece = getPieceOfPosition(destination);
        if (movePiece.getColor().opposite() == targetPiece.getColor()) {
            targetPiece.capture();
        }
        else if (movePiece.getColor() == targetPiece.getColor()) {
            throw new IllegalStateException("도착지에 같은 편의 기물이 존재하기 때문에 움직일 수 없습니다.");
        }

        board.put(origin, new None());
        board.put(destination, movePiece);
    }

    private void validateExistHurdleOnRouteWithoutDestination(ChessPiece piece, Position origin, Position destination) {
        List<Movement> route = piece.findRoute(origin, destination);
        List<Movement> routeWithoutDestination = route.subList(0, route.size() - 1);
        Position origin2 = origin;
        for (Movement movement : routeWithoutDestination) {
            if (origin2.canMove(movement)) {
                origin2 = origin2.move(movement);
                if (!getPieceOfPosition(origin2).isEmpty()) {
                    throw new IllegalStateException("경로에 장애물이 존재하여 움직일 수 없습니다.");
                }
            }
        }
    }

    private void validateExistHurdleOnRouteWithDestination(ChessPiece piece, Position origin, Position destination) {
        List<Movement> route = piece.findRoute(origin, destination);
        Position origin2 = origin;
        for (Movement movement : route) {
            if (origin2.canMove(movement)) {
                origin2 = origin2.move(movement);
                if (!getPieceOfPosition(origin2).isEmpty()) {
                    throw new IllegalStateException("경로 및 도착지에 장애물이 존재하여 움직일 수 없습니다.");
                }
            }
        }
    }
}
