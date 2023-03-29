package service;

import controller.ChessBoardElement;
import dao.ChessDao;
import dao.MoveDto;
import domain.chessboard.ChessBoard;
import domain.chessgame.ChessGame;
import domain.piece.Color;
import domain.position.Position;
import domain.position.PositionFactory;
import domain.squarestatus.SquareStatus;
import domain.type.EmptyType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ChessService {

    private static final int ROW_SIZE = 8;
    private final ChessGame chessGame;
    private final ChessDao chessDao;

    public ChessService(final ChessGame chessGame, final ChessDao chessDao) {
        this.chessGame = chessGame;
        this.chessDao = chessDao;
    }

    public void startNewGame() {
        chessDao.deleteAll();
    }

    public void initializeBoard() {
        chessGame.initializeBoard();
    }

    public void startLoadGame() {
        loadGame();
    }

    public void move(final String source, final String target) {
        chessGame.move(PositionFactory.createPosition(source), PositionFactory.createPosition(target));
        chessDao.save(new MoveDto(source, target));
    }

    public boolean isKingAlive() {
        return chessGame.isKingAlive();
    }

    public double getWhiteScore() {
        return chessGame.calculateScore(Color.WHITE);
    }

    public double getBlackScore() {
        return chessGame.calculateScore(Color.BLACK);
    }

    public List<String> getRows() {
        List<String> rows = new ArrayList<>(ROW_SIZE);
        for (int i = 0; i < ROW_SIZE; i++) {
            rows.add(convertRowToChessBoardElement(i));
        }
        return rows;
    }

    private String convertRowToChessBoardElement(final int order) {
        final ChessBoard chessBoard = chessGame.getChessBoard();
        return PositionFactory.findRow(order).stream()
                .map(chessBoard::findPosition)
                .map(this::convertPieceToElement)
                .collect(Collectors.joining());
    }

    private String convertPieceToElement(final SquareStatus squareStatus) {
        final String elementName = ChessBoardElement.getElementName(squareStatus.getType());

        if (squareStatus.isDifferentType(EmptyType.EMPTY) && squareStatus.isSameColor(Color.BLACK)) {
            return elementName.toUpperCase();
        }
        return elementName;
    }

    private void loadGame() {
        final List<MoveDto> allMove = chessDao.findAllMove();

        for (MoveDto move : allMove) {
            final Position source = PositionFactory.createPosition(move.getSource());
            final Position target = PositionFactory.createPosition(move.getTarget());

            chessGame.move(source, target);
        }
    }

}
