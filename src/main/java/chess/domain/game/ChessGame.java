package chess.domain.game;

import chess.domain.command.MoveCommand;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import java.util.List;

public class ChessGame {

    private static final String TURN_EXCEPTION_MESSAGE = "턴은 백색 말부터 시작해 한번씩 움직일 수 있습니다.";
    private static final String FRIENDLY_OCCUPIED_EXCEPTION_MESSAGE = "이동하려는 위치에 아군 말이 있습니다.";
    private static final String PIECE_OCCUPIED_IN_PATH_EXCEPTION_MESSAGE = "가는 길목에 다른 말이 있어 이동할 수 없습니다.";
    private static final String GAME_END_EXCEPTION_MESSAGE = "게임이 끝난 후에는 경기를 더 진행할 수 없습니다.";

    private Pieces chessmen;
    private Color turn;
    private boolean forceEndFlag;

    public ChessGame(boolean forceEndFlag, Pieces chessmen, Color turn) {
        this.forceEndFlag = forceEndFlag;
        this.chessmen = chessmen;
        this.turn = turn;
    }

    private ChessGame(Pieces chessmen) {
        this.forceEndFlag = false;
        this.chessmen = chessmen;
        this.turn = Color.BLACK;
    }

    public ChessGame() {
        forceEndFlag = false;
        this.chessmen = new Pieces(List.of());
    }

    public static ChessGame of(Pieces chessmen) {
        return new ChessGame(chessmen);
    }

    public static ChessGame of() {
        return new ChessGame();
    }

    public void moveChessmen(MoveCommand moveCommand) {
        Piece sourcePiece = chessmen.extractPiece(Position.of(moveCommand.getSource()));
        Position toPosition = Position.of(moveCommand.getTarget());

        checkEnd();
        validateTurn(sourcePiece);
        checkMovable(sourcePiece, toPosition);
        moveOrKill(sourcePiece, toPosition);
        changeTurn();
    }

    private void checkEnd() {
        if (isEnd()) {
            throw new IllegalArgumentException(GAME_END_EXCEPTION_MESSAGE);
        }
    }

    private void checkMovable(Piece sourcePiece, Position toPosition) {
        if (chessmen.isOccupied(toPosition)) {
            checkOccupiedByFriendly(sourcePiece, toPosition, chessmen);
        }
        checkPath(sourcePiece, toPosition, chessmen);
    }

    private void validateTurn(Piece sourcePiece) {
        if (sourcePiece.isSameColor(turn)) {
            throw new IllegalArgumentException(TURN_EXCEPTION_MESSAGE);
        }
    }

    private void checkOccupiedByFriendly(Piece sourcePiece, Position toPosition, Pieces chessmen) {
        Piece targetPiece = chessmen.extractPiece(toPosition);
        if (sourcePiece.isFriendly(targetPiece)) {
            throw new IllegalArgumentException(FRIENDLY_OCCUPIED_EXCEPTION_MESSAGE);
        }
    }

    private void checkPath(Piece sourcePiece, Position toPosition, Pieces chessmen) {
        List<Position> positions = sourcePiece.getPositionsInPath(toPosition);
        if (chessmen.isAnyPieceExistInPositions(positions)) {
            throw new IllegalArgumentException(PIECE_OCCUPIED_IN_PATH_EXCEPTION_MESSAGE);
        }
    }

    private void moveOrKill(Piece sourcePiece, Position toPosition) {
        if (chessmen.isOccupied(toPosition)) {
            killEnemy(sourcePiece, toPosition, chessmen);
            return;
        }
        sourcePiece.move(toPosition);
    }

    private void killEnemy(Piece sourcePiece, Position toPosition, Pieces chessmen) {
        Piece target = chessmen.extractPiece(toPosition);
        sourcePiece.kill(target);
        chessmen.remove(target);
    }

    private void changeTurn() {
        this.turn = turn.nextTurn();
    }

    public boolean isEnd() {
        return forceEndFlag || chessmen.hasLessThanTotalKingCount();
    }

    public void forceEnd() {
        forceEndFlag = true;
    }

    public Pieces getChessmen() {
        return chessmen;
    }

    public Color getTurn() {
        return turn;
    }

    public boolean getForceEndFlag() {
        return forceEndFlag;
    }

    public void clean() {
        chessmen = new Pieces(List.of());
    }

    @Override
    public String toString() {
        return "ChessGame{" + "chessmen=" + chessmen + '}';
    }

}
