/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    
  https://leetcode.com/problems/merge-k-sorted-lists/
  public ListNode mergeKLists(ListNode[] lists) {
    
       PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new Sorter());
        
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null){
                pq.add(lists[i]);
            }
        }
        
        ListNode preHead = new ListNode();
        ListNode prev = preHead;
        
        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            prev.next = node;
            prev = prev.next;
            
            ListNode nextNode = node.next;
            if(nextNode!=null){
                pq.add(nextNode);
            }
            
        }
        
        return preHead.next;
        
    }
  
  https://leetcode.com/problems/merge-two-sorted-lists/
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        
        while(l1!=null && l2!=null){
            if(l1.val <= l2.val){
                prev.next = l1;
                l1 = l1.next;
            }
            else {
                prev.next = l2;
                l2 = l2.next;
                
            }
            prev = prev.next;
        }
        prev.next = l1 == null?l2:l1;
        return prehead.next;
        
    }
    
    
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
    
    static class Sorter implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2){
            return o1.val - o2.val;
        }
    }
}
