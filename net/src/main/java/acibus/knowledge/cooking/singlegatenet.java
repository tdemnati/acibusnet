package acibus.knowledge.cooking;

public class singlegatenet {
private double x;
private double y;
private double x_gradient;
private double y_gradient;


public singlegatenet(double a, double b)
{
	setSinglegatenet(a,b);
	}

private void setSinglegatenet(double a, double b){
	this.x = a;
	this.y = b;
	this.x_gradient = b;
	this.y_gradient = a;
}

public double forwardMultiplygate()
{
	return x*y;
	}

public double force(double stepsize)
{
	double out;
	
	x = x + stepsize*x_gradient;
	y = y + stepsize*y_gradient;
	out = x*y;
	return out;
}

}
