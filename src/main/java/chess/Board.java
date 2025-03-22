package chess;

import chess.piece.Bishop;
import chess.piece.BlackPawn;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.WhitePawn;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {

    private final Map<Position, Piece> boardMap;

    public Board() {
        Map<Position, Piece> boardMap = new HashMap<>();

        // 흑색 기물 배치 (8행, 7행)
        boardMap.put(new Position(Column.A, Row.EIGHT), new Piece(Color.BLACK, PieceType.ROOK));
        boardMap.put(new Position(Column.B, Row.EIGHT), new Piece(Color.BLACK, PieceType.KNIGHT));
        boardMap.put(new Position(Column.C, Row.EIGHT), new Piece(Color.BLACK, PieceType.BISHOP));
        boardMap.put(new Position(Column.D, Row.EIGHT), new Piece(Color.BLACK, PieceType.QUEEN));
        boardMap.put(new Position(Column.E, Row.EIGHT), new Piece(Color.BLACK, PieceType.KING));
        boardMap.put(new Position(Column.F, Row.EIGHT), new Piece(Color.BLACK, PieceType.BISHOP));
        boardMap.put(new Position(Column.G, Row.EIGHT), new Piece(Color.BLACK, PieceType.KNIGHT));
        boardMap.put(new Position(Column.H, Row.EIGHT), new Piece(Color.BLACK, PieceType.ROOK));
        for (Column column : Column.values()) {
            boardMap.put(new Position(column, Row.SEVEN), new Piece(Color.BLACK, PieceType.PAWN));
        }

        // 백색 기물 배치 (1행, 2행)
        boardMap.put(new Position(Column.A, Row.ONE), new Piece(Color.WHITE, PieceType.ROOK));
        boardMap.put(new Position(Column.B, Row.ONE), new Piece(Color.WHITE, PieceType.KNIGHT));
        boardMap.put(new Position(Column.C, Row.ONE), new Piece(Color.WHITE, PieceType.BISHOP));
        boardMap.put(new Position(Column.D, Row.ONE), new Piece(Color.WHITE, PieceType.QUEEN));
        boardMap.put(new Position(Column.E, Row.ONE), new Piece(Color.WHITE, PieceType.KING));
        boardMap.put(new Position(Column.F, Row.ONE), new Piece(Color.WHITE, PieceType.BISHOP));
        boardMap.put(new Position(Column.G, Row.ONE), new Piece(Color.WHITE, PieceType.KNIGHT));
        boardMap.put(new Position(Column.H, Row.ONE), new Piece(Color.WHITE, PieceType.ROOK));
        for (Column column : Column.values()) {
            boardMap.put(new Position(column, Row.TWO), new Piece(Color.WHITE, PieceType.PAWN));
        }

        this.boardMap = boardMap;
    }

    public Map<Position, Piece> getBoardMap() {
        return boardMap;
    }

    public void movePiece(Position fromPosition, Position toPosition, Color turnColor) {
        Piece fromPiece = boardMap.get(fromPosition);
        System.out.println(fromPiece);

        PieceType fromPieceType = fromPiece.getPieceType();
        Color fromPieceColor = fromPiece.getColor();

        if (fromPieceType == PieceType.PAWN) {
            Piece toPiece = boardMap.get(toPosition);
            Set<Position> positions1;
            if (fromPieceColor == Color.WHITE) {
                positions1 = WhitePawn.canEat(fromPosition, toPosition);

            } else {
                positions1 = BlackPawn.canEat(fromPosition, toPosition);
            }

            if (positions1.contains(toPosition)) {
                boardMap.remove(fromPosition);
                boardMap.remove(toPosition);
                boardMap.put(toPosition, fromPiece);
                System.out.println("실행됨");
                return;
            }
        }
        if (turnColor != fromPieceColor) {
            throw new IllegalArgumentException("니턴아님");
        }

        switch (fromPieceType) {
            case KING -> kingMove(fromPosition, toPosition);
            case PAWN -> pawnMove(fromPosition, toPosition, fromPieceType, fromPieceColor);
            case KNIGHT -> knightMove(fromPosition, toPosition);
            case BISHOP -> bishopMove(fromPosition, toPosition);
            case ROOK -> rookMove(fromPosition, toPosition);
            case QUEEN -> queenMove(fromPosition, toPosition);
        }
        //  가튼 팀이라 못 가는 경우 ㅠㅠㅠㅠ
        if (boardMap.containsKey(toPosition)) {
            Piece toPiece = boardMap.get(toPosition);
            if (toPiece.getColor() == fromPieceColor) {
                throw new IllegalArgumentException("같은 팀이라 못가요. ㅠㅠ");
            }
            if (fromPieceType == PieceType.PAWN) {
                throw new IllegalArgumentException("폰은 앞에 있는 거 못먹음 ㅅㄱㅇ");
            }
            boardMap.remove(toPosition);
        }

        boardMap.remove(fromPosition);
        boardMap.put(toPosition, fromPiece);

    }

    private void kingMove(Position fromposition, Position toPosition) {
        // 중간 경로들
        Set<Position> positions = King.canMove(fromposition, toPosition);
        //중간 기물 확인
        positions.stream()
                .forEach(position -> {
                    if (boardMap.containsKey(position)) {
                        throw new IllegalArgumentException("중간에 기물 있어요");
                    }
                });


    }

    private void pawnMove(Position fromPosition, Position toPosition, PieceType pieceType, Color color) {
        if (pieceType == PieceType.PAWN) {
            if (color == Color.BLACK) {
                blackPawnMove(fromPosition, toPosition);
            }
            if (color == Color.WHITE) {
                whitePawnMove(fromPosition, toPosition);
            }
        }
    }

    private void whitePawnMove(Position fromposition, Position toPosition) {
        Set<Position> positions = WhitePawn.canMove(fromposition, toPosition);

    }

    private Set<Position> whitePawnEat(Position fromposition, Position toPosition) {
        Set<Position> positions = WhitePawn.canEat(fromposition, toPosition);
        return positions;
    }

    private void blackPawnMove(Position fromposition, Position toPosition) {
        Set<Position> positions = BlackPawn.canMove(fromposition, toPosition);

    }


    private void bishopMove(Position fromposition, Position toPosition) {
        Bishop.canMove(fromposition, toPosition);
        Set<Position> positions = Bishop.betweenPosition(fromposition, toPosition);
        positions
                .forEach(position -> {
                    if (boardMap.containsKey(position)) {
                        throw new IllegalArgumentException("중간에 기물 있어요");
                    }
                });

    }

    private void knightMove(Position fromposition, Position toPosition) {
        Set<Position> positions = Knight.canMove(fromposition, toPosition);

    }

    private void queenMove(Position fromposition, Position toPosition) {
        Set<Position> positions = Queen.canMove(fromposition, toPosition);
        Set<Position> positions2 = Bishop.betweenPosition(fromposition, toPosition);
        positions2
                .forEach(position -> {
                    if (boardMap.containsKey(position)) {
                        throw new IllegalArgumentException("중간에 기물 있어요");
                    }
                });


    }

    private void rookMove(Position fromposition, Position toPosition) {
        Set<Position> positions = Rook.canMove(fromposition, toPosition);
        // 중간 기물 체크
        Set<Position> betweenPositions = fromposition.rookBetweenPositions(toPosition);
        betweenPositions.stream()
                .forEach(position -> {
                    if (boardMap.containsKey(position)) {
                        throw new IllegalArgumentException("중간에 기물 있어요");
                    }
                });

    }


}
