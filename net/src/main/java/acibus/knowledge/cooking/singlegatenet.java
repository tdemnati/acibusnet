package acibus.knowledge.cooking;

public class singlegatenet {
private int x;
private int y;


public singlegatenet(int a, int b)
{
	setSinglegatenet(a,b);
	}

private void setSinglegatenet(int a, int b){
	this.x = a;
	this.y = b;
}

public int forwardMultiplygate()
{
	return x*y;
	}

}
