package controller;

import domain.chessgame.ChessGame;
import domain.piece.Color;
import dto.ScoreDto;
import view.OutputView;

import java.util.List;

public class Status extends GameStatus {

    protected Status(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void playTurn(final List<String> inputs) {
        final ScoreDto scoreDto = ScoreDto.of(chessGame.calculateScore(Color.WHITE), chessGame.calculateScore(Color.BLACK));

        OutputView.printNotice("> 중간 결과");
        OutputView.printScore(scoreDto);
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }
}
