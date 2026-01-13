public class CrimeCase {
    private int caseId;
    private String title;
    private String description;
    private String status;
    private String detectiveBadge;

    public CrimeCase(int caseId, String title, String description) {
        this.caseId = caseId;
        this.title = title;
        this.description = description;
        this.status = "Open";
        this.detectiveBadge = "";
    }

    public int getCaseId() {
        return caseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getDetectiveBadge() {
        return detectiveBadge;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public void assignDetective(String badge) {
        this.detectiveBadge = badge;
    }

    public String toText() {
        return caseId + "|" + title + "|" + description + "|" + status + "|" + detectiveBadge;
    }

    public static CrimeCase fromText(String line) {
        String[] parts = line.split("\\|");
        CrimeCase c = new CrimeCase(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2]
        );
        c.updateStatus(parts[3]);
        c.assignDetective(parts[4]);
        return c;
    }

    @Override
    public String toString() {
        return "Case ID: " + caseId +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nStatus: " + status +
                "\nDetective Badge: " + detectiveBadge;
    }
}
