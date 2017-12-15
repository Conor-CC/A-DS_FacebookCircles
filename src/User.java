public class User {

	private int id;
	private int circleNum;
	
	User (int id, int circleNum) {
		this.id = id;
		this.circleNum = circleNum;
	}
	
	public int getCircleNum() {
		return circleNum;
	}
	
	public void setCircleNum(int newNum){
		this.circleNum = newNum;
	}
	
	public int getId() {
		return id;
	}
}
