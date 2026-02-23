package Q3;

public class AdvancedCalculator extends Calculator {

    int[] arr;

    public AdvancedCalculator(int a, int b, int arr[]) {
        super(a, b);
        this.arr = arr;
    }

    public int sum() {
        int sum = super.sum();
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }
}
