package fill;

public class PatternFillChessboard implements PatternFill {
    @Override
    public int paint(int x, int y) {
        if(x%2 == 0) return 0xff0000;
        return 0xFF;
    }
}

