package chess.domain;

import chess.domain.command.MoveCommand;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessGame {

    private final Board board;
    private PieceColor turnColor = PieceColor.WHITE;

    public ChessGame(Map<Position, AbstractPiece> pieces) {
        this.board = new Board(pieces);
    }

    public void proceedWith(MoveCommand moveCommand) {
        board.movePiece(turnColor, moveCommand);
        changeTurn();
    }

    private void changeTurn() {
        turnColor = turnColor.enemyColor();
    }

    public boolean isRunning() {
        return board.hasBothKings();
    }

    public Map<PieceColor, Score> calculateScore() {
        Map<PieceColor, Score> scores = new HashMap<>();
        for (PieceColor color : PieceColor.values()) {
            Map<Position, AbstractPiece> pieces = board.getPiecesOf(color);
            scores.put(color, Score.of(pieces));
        }
        return scores;
    }

    public Map<Position, AbstractPiece> getPieces() {
        return board.getPieces();
    }

    public static class Board {

        private final Map<Position, AbstractPiece> pieces;

        public Board(Map<Position, AbstractPiece> pieces) {
            this.pieces = pieces;
        }

        public void movePiece(PieceColor turnColor, MoveCommand command) {
            validateMovement(turnColor, command);
            AbstractPiece sourcePiece = pieces.get(command.from());
            pieces.remove(command.from());
            pieces.put(command.to(), sourcePiece);
        }

        private void validateMovement(PieceColor turnColor, MoveCommand command) {
            validateSourceChoice(turnColor, command.from());
            validateTargetChoice(turnColor, command.to());
            validateMovable(command.from(), command.to());
            validateRoute(command.from(), command.to());
        }

        private void validateSourceChoice(PieceColor turnColor, Position source) {
            if (!pieces.containsKey(source)) {
                throw new IllegalArgumentException("source 위치에 기물이 존재하지 않습니다.");
            }
            AbstractPiece sourcePiece = pieces.get(source);
            if (!sourcePiece.isSameColor(turnColor)) {
                throw new IllegalArgumentException("현재 순서 진영의 기물이 아닙니다.");
            }
        }

        private void validateTargetChoice(PieceColor turnColor, Position target) {
            if (pieces.containsKey(target) && pieces.get(target).isSameColor(turnColor)) {
                throw new IllegalArgumentException("target 위치에 자신의 기물이 있습니다.");
            }
        }

        private void validateMovable(Position source, Position target) {
            AbstractPiece sourcePiece = pieces.get(source);
            if (!sourcePiece.isCorrectMovement(source, target, pieces.containsKey(target))) {
                throw new IllegalArgumentException("해당 기물이 움직일 수 있는 행마법이 아닙니다.");
            }
        }

        private void validateRoute(Position source, Position target) {
            AbstractPiece sourcePiece = pieces.get(source);
            if (sourcePiece.canJumpOverPieces()) {
                return;
            }
            List<Position> route = findRoute(source, target);
            for (Position node : route) {
                validatePieceOnRoute(node);
            }
        }

        private List<Position> findRoute(Position source, Position target) {
            List<Position> route = new ArrayList<>();

            int routeLength = source.calculateMaxLinearLengthTo(target);
            int xSlope = source.calculateXSlope(target, routeLength);
            int ySlope = source.calculateYSlope(target, routeLength);

            for (int step = 1; step < routeLength; step++) {
                Position routeNode = source.displacedOf(xSlope * step, ySlope * step);
                route.add(routeNode);
            }
            return route;
        }

        private void validatePieceOnRoute(Position node) {
            if (pieces.containsKey(node)) {
                throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다.");
            }
        }

        public boolean hasBothKings() {
            return countKingsOnBoard() == 2;
        }

        private long countKingsOnBoard() {
            return pieces.values()
                    .stream()
                    .filter(AbstractPiece::isKing)
                    .count();
        }

        public Map<Position, AbstractPiece> getPiecesOf(PieceColor color) {
            return pieces.keySet()
                    .stream()
                    .filter(position -> pieces.get(position).isSameColor(color))
                    .collect(Collectors.toMap(position -> position, pieces::get));
        }

        public Map<Position, AbstractPiece> getPieces() {
            return Map.copyOf(pieces);
        }
    }
}
