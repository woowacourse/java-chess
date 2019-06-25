package chess.domain;

import chess.domain.pieces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private Map<Position, Piece> boardState = new HashMap<>();
    private boolean isKingDead = false;

    public void initialize() {
        initializeRook();
        initializeKnight();
        initializeBishop();
        initializeQueen();
        initializeKing();
        initializePawn();
        initializeBlank();
    }

    private void initializeRook() {
        Position position;
        position = new Position(1, 1);
        boardState.put(position, new Rook(position, Team.WHITE));
        position = new Position(8, 1);
        boardState.put(position, new Rook(position, Team.WHITE));
        position = new Position(1, 8);
        boardState.put(position, new Rook(position, Team.BLACK));
        position = new Position(8, 8);
        boardState.put(position, new Rook(position, Team.BLACK));
    }

    private void initializeKnight() {
        Position position;
        position = new Position(2, 1);
        boardState.put(position, new Knight(position, Team.WHITE));
        position = new Position(7, 1);
        boardState.put(position, new Knight(position, Team.WHITE));
        position = new Position(2, 8);
        boardState.put(position, new Knight(position, Team.BLACK));
        position = new Position(7, 8);
        boardState.put(position, new Knight(position, Team.BLACK));
    }

    private void initializeBishop() {
        Position position;
        position = new Position(3, 1);
        boardState.put(position, new Bishop(position, Team.WHITE));
        position = new Position(6, 1);
        boardState.put(position, new Bishop(position, Team.WHITE));
        position = new Position(3, 8);
        boardState.put(position, new Bishop(position, Team.BLACK));
        position = new Position(6, 8);
        boardState.put(position, new Bishop(position, Team.BLACK));
    }

    private void initializeQueen() {
        Position position;
        position = new Position(4, 1);
        boardState.put(position, new Queen(position, Team.WHITE));
        position = new Position(4, 8);
        boardState.put(position, new Queen(position, Team.BLACK));
    }

    private void initializeKing() {
        Position position;
        position = new Position(5, 1);
        boardState.put(position, new King(position, Team.WHITE));
        position = new Position(5, 8);
        boardState.put(position, new King(position, Team.BLACK));
    }

    private void initializePawn() {
        Position position;
        for (int i = 1; i <= 8; i++) {
            position = new Position(i, 2);
            boardState.put(position, new Pawn(position, Team.WHITE));
            position = new Position(i, 7);
            boardState.put(position, new Pawn(position, Team.BLACK));
        }
    }

    private void initializeBlank() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 3; j <= 6; j++) {
                Position position = new Position(i, j);
                boardState.put(position, new Blank(position, Team.BLANK));
            }
        }
    }

    public void move(Position source, Position target, Team team) {
        if (isEmpty(source)) {
            throw new IllegalArgumentException("선택된 위치에 말이 존재하지 않습니다.");
        }
        if (!boardState.get(source).isOurPiece(team)) {
            throw new IllegalArgumentException("선택된 위치의 말이 상대편 팀의 말입니다.");
        }
        if (isSameTeam(source, target)) {
            throw new IllegalArgumentException("말을 움직이고자 하는 위치에 같은 팀의 말이 존재합니다.");
        }
        if (!boardState.get(source).canMove(target)) {
            throw new IllegalArgumentException("선택하신 말이 움직일 수 없는 위치입니다.");
        }

        if (!(boardState.get(source) instanceof Knight)) {
            checkObstacle(source, target);
        }

        Piece sourcePiece = boardState.get(source);
        Piece targetPiece = boardState.get(target);
        if (sourcePiece instanceof Pawn) {
            if (!(targetPiece instanceof Blank)
                    && !(sourcePiece.isSameTeamWith(targetPiece))
                    && !(source.getDistanceSquare(target) == 2)) {
                throw new IllegalArgumentException("잡을 수 없는 위치입니다.");
            }
            if (isEmpty(target) && source.getDistanceSquare(target) == 2) {
                throw new IllegalArgumentException("말을 움직이고자 하는 위치는 폰이 움직일 수 없는 위치입니다.");
            }
        }
        movePiece(source, target);
        boardState.get(target).move(target);
    }

    public void checkObstacle(Position source, Position target) {
        List<Position> route = source.getRoutePosition(target);
        for (Position position : route) {
            if (!(boardState.get(position) instanceof Blank)) {
                throw new IllegalArgumentException("경로에 다른 말이 있습니다.");
            }
        }
    }

    private void movePiece(Position source, Position target) {
        if (boardState.get(target) instanceof King) {
            isKingDead = true;
        }
        boardState.put(target, boardState.get(source));
        boardState.put(source, new Blank(source, Team.BLANK));
    }

    private boolean isEmpty(Position position) {
        return boardState.get(position) instanceof Blank;
    }

    private boolean isSameTeam(Position source, Position target) {
        Piece sourcePiece = boardState.get(source);
        Piece targetPiece = boardState.get(target);
        return sourcePiece.isSameTeamWith(targetPiece);
    }

    public Piece findPiece(Position position) {
        return boardState.get(position);
    }

    public boolean isKingDead() {
        return isKingDead;
    }

    public double getScore(Team team) {
        List<Piece> sameTeamPieces = boardState.keySet().stream()
                .map(position -> boardState.get(position))
                .filter(piece -> piece.isOurPiece(team))
                .collect(Collectors.toList());

        double totalScore = sameTeamPieces.stream()
                .mapToDouble(Piece::getScore)
                .sum();

        List<Pawn> pawns = sameTeamPieces.stream()
                .filter(piece -> piece instanceof Pawn)
                .map(piece -> (Pawn) piece)
                .collect(Collectors.toList());

        totalScore -= Pawn.getSameColumnSamePawnCount(pawns) * 0.5;

        return totalScore;
    }
}
