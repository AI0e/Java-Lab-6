/**
 * Represents a specific type of sweet: a Lollipop.
 * It inherits from Sweet and adds a specific property (flavor).
 */
public class Lollipop extends Sweet {

    private final String flavor;

    /**
     * Constructs a new Lollipop.
     * Note: We enforce that chocolateContent is 0 for a lollipop.
     *
     * @param name         The name of the lollipop.
     * @param weight       The weight in grams.
     * @param sugarContent The sugar content in grams.
     * @param flavor       The flavor (e.g., "Cherry", "Apple").
     */
    public Lollipop(String name, double weight, double sugarContent, String flavor) {
        super(name, weight, sugarContent, 0.0);
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    /**
     * Appends the specific details of this class to the base toString().
     */
    @Override
    public String toString() {
        return super.toString() + " | Flavor: " + flavor;
    }
}