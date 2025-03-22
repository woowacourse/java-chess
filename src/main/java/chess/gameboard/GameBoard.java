package chess.gameboard;

import java.util.HashMap;
import java.util.Map;
import chess.Color;
import chess.Position;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.request.StartPosAndEndPos;

public class GameBoard {
    private final Map<Position, Piece> board;

    public GameBoard() {
        // 보드 초기화
        board = new HashMap<>(Map.ofEntries(
                // 백팀 폰
                Map.entry(Position.from("A2"), new Pawn(Color.WHITE)),
                Map.entry(Position.from("B2"), new Pawn(Color.WHITE)),
                Map.entry(Position.from("C2"), new Pawn(Color.WHITE)),
                Map.entry(Position.from("D2"), new Pawn(Color.WHITE)),
                Map.entry(Position.from("E2"), new Pawn(Color.WHITE)),
                Map.entry(Position.from("F2"), new Pawn(Color.WHITE)),
                Map.entry(Position.from("G2"), new Pawn(Color.WHITE)),
                Map.entry(Position.from("H2"), new Pawn(Color.WHITE)),

                // 흑팀 폰
                Map.entry(Position.from("A7"), new Pawn(Color.BLACK)),
                Map.entry(Position.from("B7"), new Pawn(Color.BLACK)),
                Map.entry(Position.from("C7"), new Pawn(Color.BLACK)),
                Map.entry(Position.from("D7"), new Pawn(Color.BLACK)),
                Map.entry(Position.from("E7"), new Pawn(Color.BLACK)),
                Map.entry(Position.from("F7"), new Pawn(Color.BLACK)),
                Map.entry(Position.from("G7"), new Pawn(Color.BLACK)),
                Map.entry(Position.from("H7"), new Pawn(Color.BLACK)),

                // 백팀 룩
                Map.entry(Position.from("A1"), new Rook(Color.WHITE)),
                Map.entry(Position.from("H1"), new Rook(Color.WHITE)),

                // 흑팀 룩
                Map.entry(Position.from("A8"), new Rook(Color.BLACK)),
                Map.entry(Position.from("H8"), new Rook(Color.BLACK)),

                // 백팀 나이트
                Map.entry(Position.from("B1"), new Knight(Color.WHITE)),
                Map.entry(Position.from("G1"), new Knight(Color.WHITE)),

                // 흑팀 나이트
                Map.entry(Position.from("B8"), new Knight(Color.BLACK)),
                Map.entry(Position.from("G8"), new Knight(Color.BLACK)),

                // 백팀 비숍
                Map.entry(Position.from("C1"), new Bishop(Color.WHITE)),
                Map.entry(Position.from("F1"), new Bishop(Color.WHITE)),

                // 흑팀 비숍
                Map.entry(Position.from("C8"), new Bishop(Color.BLACK)),
                Map.entry(Position.from("F8"), new Bishop(Color.BLACK)),

                // 백팀 퀸
                Map.entry(Position.from("D1"), new Queen(Color.WHITE)),

                // 흑팀 퀸
                Map.entry(Position.from("D8"), new Queen(Color.BLACK)),

                // 백팀 킹
                Map.entry(Position.from("E1"), new King(Color.WHITE)),

                // 흑팀 킹
                Map.entry(Position.from("E8"), new King(Color.BLACK))
        ));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    private Piece pick(Position position) {
        return board.get(position);
    }

    public void move(StartPosAndEndPos moveRequest) {
        try {
            Piece pickedPiece = pick(moveRequest.startPosition());
            capturePosition(pickedPiece, moveRequest.startPosition(), moveRequest.endPosition());
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
    }

    private void capturePosition(Piece killerPiece, Position positionBeforeMove, Position destination) {
        if (positionBeforeMove.equals(destination)) {
            throw new IllegalArgumentException("제자리로는 이동할 수 없습니다.");
        }
        if (!killerPiece.isAbleToMove(positionBeforeMove, destination, board)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        board.remove(positionBeforeMove);
        board.put(destination, killerPiece);
    }
}
