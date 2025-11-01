/**
 * Represents a specific type of sweet: a Chocolate Bar.
 * It inherits from Sweet and adds a specific property (cocoa percentage).
 */
public class ChocolateBar extends Sweet {

    private final int cocoaPercentage; // e.g., 70, 85

    /**
     * Constructs a new ChocolateBar.
     * Note: We enforce that chocolateContent must be high.
     *
     * @param name            The name of the bar.
     * @param weight          The weight in grams.
     * @param sugarContent    The sugar content in grams.
     * @param cocoaPercentage The percentage of cocoa solids (e.g., 70).
     */
    public ChocolateBar(String name, double weight, double sugarContent, int cocoaPercentage) {

        super(name, weight, sugarContent, cocoaPercentage / 100.0);

        if (cocoaPercentage < 30 || cocoaPercentage > 100) {
            throw new IllegalArgumentException("Cocoa percentage must be between 30 and 100.");
        }
        this.cocoaPercentage = cocoaPercentage;
    }

    public int getCocoaPercentage() {
        return cocoaPercentage;
    }

    /**
     * Appends the specific details of this class to the base toString().
     */
    @Override
    public String toString() {

        return super.toString();
    }
}