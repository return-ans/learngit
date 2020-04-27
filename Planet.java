package planet;

public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	
	public Planet(double xP, double yP, double xV,double yV, double m, String img) {
		this.xxPos=xP;
		this.yyPos=yP;
		this.xxVel=xV;
		this.yyVel=yV;
		this.mass=m;
		this.imgFileName=img;
	}
	
	public double calcDistance(Planet other) {
		double dx=Math.abs(this.xxPos-other.xxPos);
		double dy=Math.abs(this.yyPos-other.yyPos);
		double tmp=Math.pow(dx, 2)+Math.pow(dy, 2);
		double ret=Math.sqrt(tmp);
		return ret;
	}
	
	public double calcForceExertedBy(Planet other) {
		double G=6.67e-11;
		double dis=calcDistance(other);
		double ret=(G*this.mass*other.mass)/Math.pow(dis,2);
		return ret;
	}
	
	public double calcForceExertedByX(Planet other) {
		//坐标分力 注意方向正负
		double dis=calcDistance(other);
		double F=calcForceExertedBy(other);
		double dx=other.xxPos-this.xxPos;
		double ret=F*dx/dis;
		return ret;
	}
	
	public double calcForceExertedByY(Planet other) {
		//坐标分力 注意方向正负
		double dis=calcDistance(other);
		double F=calcForceExertedBy(other);
		double dy=other.yyPos-this.yyPos;
		double ret=F*dy/dis;
		return ret;
	}
	
	public double calcNetForceExertedByX(Planet[] planetsList) {
		double ret=0.00;
		for(Planet p:planetsList) 
			ret+=calcForceExertedByX(p);
		return ret;
	}
	
	public double calcNetForceExertedByY(Planet[] planetsList) {
		double ret=0.00;
		for(Planet p:planetsList) 
			ret+=calcForceExertedByY(p);
		return ret;
	}
	
	public void update(double dt,double fX,double fY) {
		double ax=fX/this.mass;
		double ay=fY/this.mass;
		double newVx=this.xxVel+dt*ax;
		double newVy=this.yyVel+dt*ay;
		double newPx=this.xxPos+dt*newVx;
		double newPy=this.yyPos+dt*newVy;
		this.xxPos=newPx;
		this.yyPos=newPy;
		this.xxVel=newVx;
		this.yyVel=newVy;
	}
	
	public void draw() {
		//位置 文件路径
		//直接加在原有的图片上面
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}
	
	public Planet(Planet p) {
		
		
	}
	
	public static void main(String[] args) {
		// TODO 不能含有main
		double ax=0.0,ay=0.0,axv=3.0,ayv=5.0,am=1.0;
		String aimg="";
		//double bx=0.0,by=0.0,bxv=0.0,byv=0.0,bm=0.0;
		//String bimg="";
		
		Planet a=new Planet(ax, ay, axv ,ayv, am,aimg);
		//Planet b=new Planet(bx, by, byv ,byv, bm,bimg);
		System.out.println("ax="+a.xxPos);
		System.out.println("ay="+a.yyPos);
		System.out.println("avx="+a.xxVel);
		System.out.println("avy="+a.yyVel);
		System.out.println();
		a.update(1.0, -5.0, -2.0);
		System.out.println("ax="+a.xxPos);
		System.out.println("ay="+a.yyPos);
		System.out.println("avx="+a.xxVel);
		System.out.println("avy="+a.yyVel);
		
		
	}

}
