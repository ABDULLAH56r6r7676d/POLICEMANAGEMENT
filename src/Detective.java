public class Detective extends Person {
    private String badgeNumber;

    public Detective(String name, String badgeNumber) {
        super(name);
        this.badgeNumber = badgeNumber;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public String toText() {
        return name + "|" + badgeNumber;
    }

    public static Detective fromText(String line) {
        String[] parts = line.split("\\|");
        return new Detective(parts[0], parts[1]);
    }

    @Override
    public String getDetails() {
        return "Detective: " + name + " (Badge: " + badgeNumber + ")";
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
