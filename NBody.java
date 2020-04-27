package planet;

public class NBody {
	
	@SuppressWarnings("unused")
	//����뾶
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}
	
	@SuppressWarnings("unused")
	public static Planet[] readPlanets(String fileName) {
		//����Planets����
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
		//����args[]�б� 
		//��һ�� double T
		double T=Double.parseDouble(args[0]);
		//�ڶ��� double dt
		double dt=Double.parseDouble(args[1]);
		//������ String filename
		String filename=args[2];
		
		double uniRadius=NBody.readRadius(filename);
		//Ҫ���û����С
		Planet[] Planets=NBody.readPlanets(filename);
		//������������
		
		//������
		StdDraw.setScale(-uniRadius,uniRadius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "");
		
		//������
		for(Planet p:Planets) {
			p.draw();
		}
		
		//����
		StdDraw.enableDoubleBuffering();
		//ѭ�� ÿһ֡
		for(double t=0;t<=T;t+=dt) {
			double[] xForces=new double[Planets.length];
			double[] yForces=new double[Planets.length];
			
			//ÿ������net force
			for(int i=0;i<Planets.length;i++) {
				//net force
				//�������ʩ�ӵ�
				xForces[i]=Planets[i].calcNetForceExertedByX(Planets);
				yForces[i]=Planets[i].calcNetForceExertedByY(Planets);
			}
			//ÿһ������ ����һ�������λ��
			for(int i=0;i<Planets.length;i++) {
				Planets[i].update(dt, xForces[i], yForces[i]);
			}
			//ÿһ������ ��һ���µĻ���
			//������
			StdDraw.picture(0, 0, "");
			//������
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
