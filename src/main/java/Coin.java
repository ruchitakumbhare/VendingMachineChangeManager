public class Coin {

    public Coin(String name, Double value)
    {
        this.Name = name;
        this.value = value;
    }

    private String Name;

    public String getName() {
        return Name;
    }

    public Double getValue() {
        return value;
    }

    private Double value;

    @Override
    public String toString() {
        return "Coin{" +
                "Name='" + Name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.Name.hashCode();
        long creditsLong = Double.doubleToLongBits(this.value);
        result = 31 * result + (int) (creditsLong ^ (creditsLong >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        // null check
        if (o == null) {
            return false;
        }

        // this instance check
        if (this == o) {
            return true;
        }

        // instanceof Check and actual value check
        if ((o instanceof Coin) && (((Coin) o).getValue() == this.value)) {
            return true;
        } else {
            return false;
        }
    }

    public Coin createNew(){
        return new Coin(this.Name, this.value);
    }
}
