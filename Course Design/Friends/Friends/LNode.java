package Friends;

public class LNode {

	private int data;
	private LNode preNode;
	private LNode nextNode;

	public LNode() {
		this(0, null, null);
	}

	public LNode(int data, LNode preNode, LNode nextNode) {
		setData(data);
		setPreNode(preNode);
		setNextNode(nextNode);
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public LNode getPreNode() {
		return preNode;
	}

	public void setPreNode(LNode preNode) {
		this.preNode = preNode;
	}

	public LNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(LNode nextNode) {
		this.nextNode = nextNode;
	}

}
