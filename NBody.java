package planet;

public class NBody {
	
	@SuppressWarnings("unused")
	//宇宙半径
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}
	
	@SuppressWarnings("unused")
	public static Planet[] readPlanets(String fileName) {
		//返回Planets对象
		In in = new In(fileName);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		Planet[] retList=new Planet[firstItemInFile];
		for(int i=0;i<firstItemInFile;i++) {
			double x=in.readDouble();;
			double y=in.readDouble();;
			double xv=in.readDouble();;
			double yv=in.readDouble();;
			double m=in.readDouble();;
			String img=in.readString();;
			Planet tmp=new Planet(x,y,xv,yv,m,img);
			retList[i]=tmp;
		}
		return retList;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//传进args[]列表 
		//第一个 double T
		double T=Double.parseDouble(args[0]);
		//第二个 double dt
		double dt=Double.parseDouble(args[1]);
		//第三个 String filename
		String filename=args[2];
		
		double uniRadius=NBody.readRadius(filename);
		//要设置画面大小
		Planet[] Planets=NBody.readPlanets(filename);
		//读出所有星球
		
		//画背景
		StdDraw.setScale(-uniRadius,uniRadius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "");
		
		//画星球
		for(Planet p:Planets) {
			p.draw();
		}
		
		//更新
		StdDraw.enableDoubleBuffering();
		//循环 每一帧
		for(double t=0;t<=T;t+=dt) {
			double[] xForces=new double[Planets.length];
			double[] yForces=new double[Planets.length];
			
			//每个计算net force
			for(int i=0;i<Planets.length;i++) {
				//net force
				//别的星球施加的
				xForces[i]=Planets[i].calcNetForceExertedByX(Planets);
				yForces[i]=Planets[i].calcNetForceExertedByY(Planets);
			}
			//每一个画面 更新一次星球的位置
			for(int i=0;i<Planets.length;i++) {
				Planets[i].update(dt, xForces[i], yForces[i]);
			}
			//每一个画面 画一次新的画面
			//画背景
			StdDraw.picture(0, 0, "");
			//画星球
			for(Planet p:Planets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			
		}	
		
		StdOut.printf("%d\n", Planets.length);
		StdOut.printf("%.2e\n", uniRadius);
		for (int i = 0; i < Planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
		                  Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);   
		}
		
		
	}

}
