public class SearchPlayer {

    private NodePlayer head;

    public SearchPlayer(NodePlayer head) {
        this.head = head;
    }

    public void linearSearch(double targetPPG) {
        System.out.println("\nMencari Pemain dengan PPG " + targetPPG + " Menggunakan Linear Search");
        long start = System.nanoTime();

        boolean found = false;
        for (NodePlayer current = head; current != null; current = current.next) {
            if (Math.abs(current.ppg - targetPPG) < 0.1) {
                printPlayer(current);
                found = true;
            }
        }

        double elapsed = (System.nanoTime() - start) / 1_000_000.0;
        if (!found) System.out.println("Tidak ada pemain dengan PPG " + targetPPG);
        System.out.printf("Elapsed Time is %.3f msec%n", elapsed);
    }

    public NodePlayer binarySearch(double targetPPG) {
        System.out.println("\nMencari Pemain dengan PPG " + targetPPG + " Menggunakan Binary Search");
        long start = System.nanoTime();

        int size = getSize();
        NodePlayer[] arr = toArray(size);

        int left = 0, right = size - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            double midPPG = arr[mid].ppg;

            if (Math.abs(midPPG - targetPPG) < 0.5) {
                printPlayer(arr[mid]);
                printElapsed(start);
                return arr[mid];
            }

            if (midPPG < targetPPG) right = mid - 1;
            else left = mid + 1;
        }

        System.out.println("Tidak ada pemain dengan PPG " + targetPPG);
        printElapsed(start);
        return null;
    }

    public void mvpSearch(double minPPG) {
        System.out.println("\n=== MVP CANDIDATE SEARCH (PPG ≥ " + minPPG + ") ===");

        boolean found = false;
        for (NodePlayer current = head; current != null; current = current.next) {
            if (current.ppg >= minPPG) {
                printPlayer(current);
                found = true;
            } else break;
        }

        if (!found)
            System.out.println("Tidak ditemukan pemain dengan PPG minimal " + minPPG);
    }

    private int getSize() {
        int count = 0;
        for (NodePlayer current = head; current != null; current = current.next) count++;
        return count;
    }

    private NodePlayer[] toArray(int size) {
        NodePlayer[] arr = new NodePlayer[size];
        NodePlayer current = head;
        for (int i = 0; i < size && current != null; i++) {
            arr[i] = current;
            current = current.next;
        }
        return arr;
    }

    private void printPlayer(NodePlayer p) {
        System.out.printf("Player: %s | Jersey: %d | PPG: %.1f%n", p.playerName, p.jersey, p.ppg);
    }

    private void printElapsed(long startTime) {
        double elapsed = (System.nanoTime() - startTime) / 1_000_000.0;
        System.out.printf("Elapsed Time is %.3f msec%n", elapsed);
    }
}
