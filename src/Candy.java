/**
 * Represents a specific type of sweet: a Candy.
 * It inherits from Sweet and adds a specific property (filling).
 */
public class Candy extends Sweet {

    private final String fillingType;

    /**
     * Constructs a new Candy.
     *
     * @param name             The name of the candy.
     * @param weight           The weight in grams.
     * @param sugarContent     The sugar content in grams.
     * @param chocolateContent The chocolate content percentage (0.0 to 1.0).
     * @param fillingType      The type of filling.
     */
    public Candy(String name, double weight, double sugarContent, double chocolateContent, String fillingType) {
        super(name, weight, sugarContent, chocolateContent);
        this.fillingType = fillingType;
    }

    public String getFillingType() {
        return fillingType;
    }

    /**
     * Appends the specific details of this class to the base toString().
     */
    @Override
    public String toString() {
        return super.toString() + " | Filling: " + fillingType;
    }
}