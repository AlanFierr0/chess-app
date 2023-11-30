package chess.logic.capablancaGame;

import common.logic.WinCondition;
import common.models.Board;
import common.models.Coordinate;
import common.models.Piece;
import common.results.MoveResult;

import java.util.Objects;

public class FirstToEat implements WinCondition {

    @Override
    public MoveResult<Board, Boolean> checkWin(Board board, Piece piece, MoveResult<Board, Boolean> move, Coordinate toSquare) {
        if (!Objects.equals(board.getSquare(toSquare).getPiece().getName(), "null")) {
            return new MoveResult<>(move.successfulResult(), true, "CheckMate");
        }
        else return move;
    }
}
