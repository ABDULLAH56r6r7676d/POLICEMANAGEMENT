import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CaseManager {

    private List<CrimeCase> cases = new ArrayList<>();
    private List<Detective> detectives = new ArrayList<>();

    // ---------------- ADD CASE (USER ENTERS ID) ----------------
    public boolean addCase(int caseId, String title, String desc) {

        // Linear search to prevent duplicate Case ID
        for (CrimeCase c : cases) {
            if (c.getCaseId() == caseId) {
                return false;
            }
        }

        cases.add(new CrimeCase(caseId, title, desc));
        return true;
    }

    public void addDetective(String name, String badge) {
        detectives.add(new Detective(name, badge));
    }

    public void assignDetective(int caseId, String badge) {

        // Check if detective exists
        boolean exists = false;
        for (Detective d : detectives) {
            if (d.getBadgeNumber().equals(badge)) {
                exists = true;
                break;
            }
        }
        if (!exists) return;

        // Linear search for case
        for (CrimeCase c : cases) {
            if (c.getCaseId() == caseId) {
                c.assignDetective(badge);
                return;
            }
        }
    }

    public void updateStatus(int caseId, String status) {
        for (CrimeCase c : cases) {
            if (c.getCaseId() == caseId) {
                c.updateStatus(status);
                return;
            }
        }
    }

    public List<CrimeCase> getCases() {
        return cases;
    }

    // ---------------- MANUAL BUBBLE SORT ----------------
    public void bubbleSortCasesById() {
        int n = cases.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (cases.get(j).getCaseId() > cases.get(j + 1).getCaseId()) {
                    CrimeCase temp = cases.get(j);
                    cases.set(j, cases.get(j + 1));
                    cases.set(j + 1, temp);
                }
            }
        }
    }

    public void bubbleSortCasesByStatus() {
        int n = cases.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (cases.get(j).getStatus()
                        .compareTo(cases.get(j + 1).getStatus()) > 0) {

                    CrimeCase temp = cases.get(j);
                    cases.set(j, cases.get(j + 1));
                    cases.set(j + 1, temp);
                }
            }
        }
    }

    // ---------------- FILE HANDLING ----------------
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter("cases.txt")) {
            for (CrimeCase c : cases) {
                pw.println(c.toText());
            }
        } catch (IOException e) {
            System.out.println("Error saving cases");
        }

        try (PrintWriter pw = new PrintWriter("detectives.txt")) {
            for (Detective d : detectives) {
                pw.println(d.toText());
            }
        } catch (IOException e) {
            System.out.println("Error saving detectives");
        }
    }

    public void loadFromFile() {
        cases.clear();
        detectives.clear();

        try (BufferedReader br = new BufferedReader(new FileReader("cases.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                cases.add(CrimeCase.fromText(line));
            }
        } catch (IOException e) {
            System.out.println("No cases file found");
        }

        try (BufferedReader br = new BufferedReader(new FileReader("detectives.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                detectives.add(Detective.fromText(line));
            }
        } catch (IOException e) {
            System.out.println("No detectives file found");
        }
    }
}
