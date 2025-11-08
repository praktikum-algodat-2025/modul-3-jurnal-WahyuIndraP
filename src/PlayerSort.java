public class PlayerSort {
    private NodePlayer head;

    public PlayerSort(NodePlayer head) {
        this.head = head;
    }

    public NodePlayer getHead() {
        return head;
    }

    public void mergeSort(boolean ascending) {
        head = mergeSortInternal(head, ascending);
    }

    private NodePlayer mergeSortInternal(NodePlayer node, boolean ascending) {
        if (node == null || node.next == null) return node;

        NodePlayer middle = getMiddle(node);
        NodePlayer nextOfMiddle = middle.next;
        middle.next = null;

        NodePlayer left = mergeSortInternal(node, ascending);
        NodePlayer right = mergeSortInternal(nextOfMiddle, ascending);

        return merge(left, right, ascending);
    }

    private NodePlayer merge(NodePlayer a, NodePlayer b, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;

        NodePlayer result;
        boolean cond = ascending ? a.ppg <= b.ppg : a.ppg >= b.ppg;

        if (cond) {
            result = a;
            result.next = merge(a.next, b, ascending);
        } else {
            result = b;
            result.next = merge(a, b.next, ascending);
        }
        return result;
    }

    private NodePlayer getMiddle(NodePlayer node) {
        if (node == null) return null;

        NodePlayer slow = node;
        NodePlayer fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void quickSort(boolean ascending) {
        head = quickSortInternal(head, ascending);
    }

    private NodePlayer quickSortInternal(NodePlayer node, boolean ascending) {
        if (node == null || node.next == null) return node;

        NodePlayer pivot = node;
        NodePlayer lessHead = null, lessTail = null;
        NodePlayer greaterHead = null, greaterTail = null;

        for (NodePlayer curr = node.next; curr != null; ) {
            NodePlayer next = curr.next;
            curr.next = null;
            boolean cond = ascending ? curr.ppg < pivot.ppg : curr.ppg > pivot.ppg;

            if (cond) {
                if (lessHead == null) lessHead = lessTail = curr;
                else lessTail = lessTail.next = curr;
            } else {
                if (greaterHead == null) greaterHead = greaterTail = curr;
                else greaterTail = greaterTail.next = curr;
            }
            curr = next;
        }

        lessHead = quickSortInternal(lessHead, ascending);
        greaterHead = quickSortInternal(greaterHead, ascending);

        return concat(lessHead, pivot, greaterHead);
    }

    private NodePlayer concat(NodePlayer lessHead, NodePlayer pivot, NodePlayer greaterHead) {
        if (lessHead != null) {
            NodePlayer tail = getTail(lessHead);
            tail.next = pivot;
            pivot.next = greaterHead;
            return lessHead;
        } else {
            pivot.next = greaterHead;
            return pivot;
        }
    }

    private NodePlayer getTail(NodePlayer node) {
        while (node != null && node.next != null)
            node = node.next;
        return node;
    }

}
