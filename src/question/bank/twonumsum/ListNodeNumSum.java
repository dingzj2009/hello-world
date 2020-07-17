package question.bank.twonumsum;

/**
 * 难度：中等
 *
 */
public class ListNodeNumSum {
	public static void main(String[] args){
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		ListNode sumNode = addTwoNumbers(l1, l2);
		System.out.println("-----测试-----");
		String input = "(" + getNodeStr(l1) + ") + " + "(" + getNodeStr(l2) + ")";
		System.out.println("输入:" + input);
		System.out.println("输出:" + getNodeStr(sumNode));
		
		l1 = new ListNode(9);
		l1.next = new ListNode(9);
		l2 = new ListNode(1);
		sumNode = addTwoNumbers(l1, l2);
		input = "(" + getNodeStr(l1) + ") + " + "(" + getNodeStr(l2) + ")";
		System.out.println("输入:" + input);
		System.out.println("输出:" + getNodeStr(sumNode));
		System.out.println("------------------");
	}
	
	public static String getNodeStr(ListNode node){
		StringBuffer sb = new StringBuffer();
		if(node == null){
			return sb.toString();
		}
		ListNode tmp = node;
		while(tmp != null){
			sb.append(tmp.val);
			if(tmp.next != null){
				sb.append(" -> ");
			}
			tmp = tmp.next;
		}
		return sb.toString();
	}
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	    ListNode dummyHead = new ListNode(0);
	    ListNode p = l1, q = l2, curr = dummyHead;
	    int carry = 0;
	    while (p != null || q != null) {
	        int x = (p != null) ? p.val : 0;
	        int y = (q != null) ? q.val : 0;
	        int sum = carry + x + y;
	        carry = sum / 10;
	        curr.next = new ListNode(sum % 10);
	        curr = curr.next;
	        if (p != null) p = p.next;
	        if (q != null) q = q.next;
	    }
	    if (carry > 0) {
	        curr.next = new ListNode(carry);
	    }
	    return dummyHead.next;
	}
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { 
		val = x;
	}
}
