package c.m.utils;

public class TSettings {
	double ymax;
	double ymin;
	double xmin;
	double xmax;

	public TSettings(double xmin, double xmax, double ymin, double ymax) {
		super();
		this.ymax = ymax;
		this.ymin = ymin;
		this.xmin = xmin;
		this.xmax = xmax;
	}

	public double getYmax() {
		return ymax;
	}

	public void setYmax(double ymax) {
		this.ymax = ymax;
	}

	public double getYmin() {
		return ymin;
	}

	public void setYmin(double ymin) {
		this.ymin = ymin;
	}

	public double getXmin() {
		return xmin;
	}

	public void setXmin(double xmin) {
		this.xmin = xmin;
	}

	public double getXmax() {
		return xmax;
	}

	public void setXmax(double xmax) {
		this.xmax = xmax;
	}

}
