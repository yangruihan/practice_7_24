package Friends;

import java.util.HashMap;

public class LinkList {

	private int length;
	private LNode head;
	private LNode tail;

	public LinkList() {
		length = 0;
		head = new LNode();
		tail = head;
	}

	public void addNode(int data) {
		LNode newNode = new LNode(data, tail, head);
		tail.setNextNode(newNode);
		tail = newNode;
		length++;
	}

	public void delTailNode() {
		if (length == 0) {
			System.out.println("当前没有结点");
			return;
		}
		tail.getPreNode().setNextNode(head);
		tail.getNextNode().setPreNode(tail.getPreNode());
	}

	public void print() {
		int i = 0;
		LNode temp = head.getNextNode();
		People tempPeople;
		while (temp != head) {
			tempPeople = Client.contacts.get(temp.getData());
			System.out.println((++i) + "." + tempPeople.getName() + " "
					+ tempPeople.getGender() + " " + tempPeople.getPhoneNum1()
					+ " " + tempPeople.getGroupName());
			temp = temp.getNextNode();
		}
	}

	public void toArray(int[] a) {
		int i = 0;
		LNode temp = head.getNextNode();
		while (temp != head) {
			a[i++] = temp.getData();
			temp = temp.getNextNode();
		}
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public LNode getHead() {
		return head;
	}

	public void setHead(LNode head) {
		this.head = head;
	}
}
