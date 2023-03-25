package controller;

import domain.chessgame.ChessGame;
import domain.piece.Color;
import dto.ChessBoardDto;
import dto.ScoreDto;
import view.OutputView;

import java.util.List;

public final class End extends GameStatus {

    End(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void playTurn(final List<String> inputs) {
        OutputView.printNotice("> 최종 결과");

        final ChessBoardDto chessBoardDTO = ChessBoardDto.from(chessGame.getChessBoard());
        final ScoreDto scoreDto = ScoreDto.of(chessGame.calculateScore(Color.WHITE), chessGame.calculateScore(Color.BLACK));

        OutputView.printChessBoard(chessBoardDTO);
        OutputView.printScore(scoreDto);
    }

    @Override
    public boolean isKeepGaming() {
        return false;
    }

}
