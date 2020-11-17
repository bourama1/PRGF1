package fill;

public class PatternFillCross implements PatternFill {

    @Override
    public int paint(int x, int y) {
        if(x%2 == 0 && y%6 == 0 || x%6 == 0 && y%2 == 0) return 0xff0000;
        return 0xFF;
    }
}
