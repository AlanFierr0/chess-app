package checkers.logic;

import common.logic.LegalMove;
import common.logic.PieceMover;
import common.logic.PossibleMovements;
import common.logic.WinCondition;
import common.models.Board;
import common.models.Coordinate;
import common.models.Piece;
import common.models.SideColor;
import common.moves.Move;
import common.results.MoveResult;

import java.util.ArrayList;
import java.util.List;

public class CheckersLegalMove implements LegalMove {
    private final PieceMover pieceMover = new PieceMover();
    private final PossibleMovements possibleMovements = new PossibleMovements();

    @Override
    public MoveResult<Board, Boolean> movePiece(Piece piece, Coordinate toSquare, Board board, Coordinate initial, List<Move> moves, WinCondition winCondition) {
        return loopUntilCantEat(piece, toSquare, board, initial, moves, winCondition);
    }

    private MoveResult<Board, Boolean> loopUntilCantEat(Piece piece, Coordinate toSquare, Board board, Coordinate initial, List<Move> moves, WinCondition winCondition) {
        if (canEatRule(piece, board, toSquare)) {
            return new MoveResult<>(board, true, "always eat Rule unfollowed");
        }
        MoveResult<Board, Boolean> move = pieceMover.check(board, initial, toSquare, moves, piece, board.getSquare(toSquare).getPiece());
        if (move.errorResult())
            return move;
        else {
            return winCondition.checkWin(board, piece, move, toSquare);
        }
    }

    private boolean canEatRule(Piece piece, Board board, Coordinate toSquare) {
        List<Coordinate> possibleMoves = new ArrayList<>();
        for (Piece piece1 : board.getCurrentPieces()) {
            if (piece1.getColor() == piece.getColor())
                possibleMoves.addAll(possibleMovements.getPossibleMovements(board, piece1, board.getSquareOfPiece(piece1).successfulResult().get()));
        }
        for (Coordinate possibleMove : possibleMoves) {
            if (isNotEatingWhenPossible(piece, board, toSquare, possibleMove)) {
                return true;
            }
        }
        return false;
    }
    private boolean isNotEatingWhenPossible(Piece piece, Board board, Coordinate toSquare, Coordinate possibleMove) {
        boolean dif = pieceOfDifferentColor(piece, board, possibleMove);
        boolean position = possibleMove.column() == toSquare.column() && possibleMove.row() == toSquare.row();
        return dif && !position;
    }

    private boolean pieceOfDifferentColor(Piece piece, Board board, Coordinate toSquare) {
        SideColor color = board.getSquare(toSquare).getPiece().getColor();
        if (color == SideColor.Black)
            return piece.getColor() == SideColor.White;
        else if (color == SideColor.White)
            return piece.getColor() == SideColor.Black;
        else
            return false;
    }
}
