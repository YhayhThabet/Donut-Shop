
public class Customer {
	public int waitTime;
	public int serviceTime;
	
	public Customer(int randNum) {
		waitTime = 0;
		serviceTime = randNum;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	
}
