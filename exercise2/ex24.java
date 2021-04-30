class Point { 
	private int x = 0; 
	private int y = 0; 
	public Point(int x, int y) { 
		this.x = x; 
		this.y = y; 
	} 
}

class Rectangle {
    private int width = 0;
    private int height = 0;
    private Point origin;

    public Rectangle() {
		this.origin = new Point(0, 0);
    }
	
    public Rectangle(Point p) {
		this.origin = p;
    }
	
    public Rectangle(int w, int h) {
		this.width = w;
		this.height= h;
    }
	
    public Rectangle(Point p, int w, int h) {
		this.origin = p;
		this.width = w;
		this.height = h;
    }

    // a method for computing the area of the rectangle
    public int area() {
		return width * height;
    }
}

public class ex24 {
	public static void main (String args[]){
		Rectangle r1 = new Rectangle();
		Rectangle r2 = new Rectangle(new Point(4,5));
		Rectangle r3 = new Rectangle(4, 5);
		Rectangle r4 = new Rectangle(new Point(4,5), 4, 5);

		System.out.println("R1: " + r1.area());
		System.out.println("R2: " + r2.area());
		System.out.println("R3: " + r3.area());
		System.out.println("R4: " + r4.area());
	}
	
}


