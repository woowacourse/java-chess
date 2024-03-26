package chess.domain.chessGame;

import chess.domain.chessGame.generator.SpaceGenerator;
import chess.domain.piece.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {

    private final List<Space> spaces;
    private final Turn turn;

    public ChessBoard(SpaceGenerator spaceGenerator, Turn turn) {
        this.spaces = spaceGenerator.generateSpaces();
        this.turn = turn;
    }

    public static ChessBoard of(SpaceGenerator spaceGenerator) {
        return new ChessBoard(spaceGenerator, Turn.create());
    }

    public void move(Position from, Position to) {
        validateActiveGame();
        Space fromSpace = findSpace(from);
        Space toSpace = findSpace(to);
        boolean wasKing = toSpace.hasKing();

        validateTurn(fromSpace);
        fromSpace.movePiece(toSpace, spaces);
        changeTurnBy(wasKing);
    }

    private void validateActiveGame() {
        if (isActive()) {
            return;
        }
        throw new IllegalStateException("게임이 활성화되지 않았습니다");
    }

    private Space findSpace(Position position) {
        return spaces.stream()
                .filter(space -> space.isSamePosition(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Space가 없습니다"));
    }

    private void validateTurn(Space fromSpace) {
        if (fromSpace.isValidTurn(turn)) {
            return;
        }
        throw new IllegalStateException("상대 플레이어의 차례입니다");
    }

    private void changeTurnBy(boolean wasKing) {
        if (wasKing) {
            endGame();
            return;
        }
        turn.oppositeTurn();
    }

    public void startGame() {
        turn.startGame();
    }

    public void endGame() {
        turn.stopGame();
    }

    public boolean isActive() {
        return turn.isActive();
    }

    public boolean isBlackKingOn(Position position) {
        Space space = findSpace(position);
        return space.hasKing() && space.hasColor(Color.BLACK);
    }

    public boolean isWhiteKingOn(Position position) {
        Space space = findSpace(position);
        return space.hasKing() && space.hasColor(Color.WHITE);
    }

    public BoardScore calculateScore(Color color) {
        return Arrays.stream(File.values())
                .map(file -> calculateScoreInFile(color, file))
                .map(BoardScore::create)
                .reduce(BoardScore::concat)
                .orElse(BoardScore.zero());
    }

    private FileScore calculateScoreInFile(Color color, File file) {
        return spaces.stream()
                .filter(space -> space.isSameFile(file))
                .filter(space -> space.hasColor(color))
                .map(space -> FileScore.create(file, space))
                .reduce(FileScore::concat)
                .orElse(FileScore.zero(file));
    }

    public List<String> showBoard() {
        return spaces.stream()
                .map(Space::pieceCharacter)
                .toList();
    }
}
