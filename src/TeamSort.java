public class TeamSort {

    private NodeTeam head;

    public TeamSort(NodeTeam head) {
        this.head = head;
    }

    public NodeTeam quickSort(boolean ascending) {
        head = quickSortInternal(head, ascending);
        return head;
    }

    private NodeTeam quickSortInternal(NodeTeam head, boolean ascending) {
        if (head == null || head.next == null) {
            return head;
        }

        NodeTeam pivot = head;
        NodeTeam lessHead = null, lessTail = null;
        NodeTeam greaterHead = null, greaterTail = null;
        NodeTeam current = head.next;

        while (current != null) {
            NodeTeam next = current.next;
            current.next = null;

            if ((ascending && current.wins < pivot.wins) || (!ascending && current.wins > pivot.wins)) {
                if (lessHead == null) lessHead = lessTail = current;
                else {
                    lessTail.next = current;
                    lessTail = current;
                }
            } else {
                if (greaterHead == null) greaterHead = greaterTail = current;
                else {
                    greaterTail.next = current;
                    greaterTail = current;
                }
            }
            current = next;
        }

        lessHead = quickSortInternal(lessHead, ascending);
        greaterHead = quickSortInternal(greaterHead, ascending);

        return concat(lessHead, pivot, greaterHead);
    }

    private NodeTeam concat(NodeTeam lessHead, NodeTeam pivot, NodeTeam greaterHead) {
        NodeTeam head = null;
        NodeTeam tail = null;

        if (lessHead != null) {
            head = lessHead;
            tail = getTail(lessHead);
            tail.next = pivot;
        } else {
            head = pivot;
        }

        pivot.next = greaterHead;
        return head;
    }

    private NodeTeam getTail(NodeTeam node) {
        while (node != null && node.next != null) node = node.next;
        return node;
    }

    public NodeTeam getHead() {
        return head;
    }
}
